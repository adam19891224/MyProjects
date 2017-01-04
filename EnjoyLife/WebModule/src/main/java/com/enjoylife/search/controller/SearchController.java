package com.enjoylife.search.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsESService;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.view.Page;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Adam
 * 2016/9/29
 */
@RestController
public class SearchController extends BaseController {

    @Resource
    private IBlogsESService esService;

    /**
     * 进入分类一览页
     */
    @RequestMapping(value = "/genre/{name}/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public String genre(ModelMap map, @PathVariable String name, @PathVariable Integer num, HttpServletRequest request){

        map.addAttribute("isSeries", YesNoTypeEnum.Yes.getCode());

        Page<NewArticle> page = new Page<NewArticle>();
        name = HtmlUtils.htmlEscape(name);
        page.setTypeName(name);
        page.setPage(num);
        page = blogsService.selectTypeArticlesByPage(page);

        map.addAttribute("result", page.getResultList());
        map.addAttribute("totalPages", page.getTotalPages());
        map.addAttribute("page", num);

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        map.addAttribute("dataType", "category");

        try {
            return toPjax(request, map, "category");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

    /**
     * 进入搜索页
     */
    @RequestMapping(value = "/query/{name}/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public String keyword(ModelMap map, @PathVariable String name, @PathVariable Integer num, HttpServletRequest request){

        map.addAttribute("isSeries", YesNoTypeEnum.Yes.getCode());

        Page<NewArticle> page = new Page<NewArticle>();
        //封装查询
        name = HtmlUtils.htmlEscape(name.trim());
        page.setKw(name);
        page.setEsPage(num);
        Map<String, Object> resM = esService.selectArticlesHighlightByPage(page);

        map.addAttribute("result", resM.get("result"));
        map.addAttribute("totalPages", resM.get("totalPage"));
        map.addAttribute("page", num);
        map.addAttribute("keyword", name);

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        map.addAttribute("dataType", "query");

        try {
            return toPjax(request, map, "search");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }
}
