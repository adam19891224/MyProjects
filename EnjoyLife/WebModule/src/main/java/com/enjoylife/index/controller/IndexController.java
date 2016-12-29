package com.enjoylife.index.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.view.Page;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@RestController
public class IndexController extends BaseController {

    /**
     * 首页方法
     * @param map
     */
    @RequestMapping({"/", "/index"})
    public String index(ModelMap map, Page<NewArticle> page, HttpServletRequest request){

        page = blogsService.selectArticlesByPage(page);

        map.addAttribute("result", page.getResultList());
        map.addAttribute("totalArticles", page.getTotalCounts());
        map.addAttribute("totalPages", page.getTotalPages());
        map.addAttribute("page", page.getPage());
        map.addAttribute("isIndex", YesNoTypeEnum.Yes.getCode());

        //首页
        map.addAttribute("dataType", "index");

        //查询分类数
        super.getTotalTypesToMap(map);

        try {
            return toPjax(request, map, "index");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

    @RequestMapping("/index/{num}")
    public String page(ModelMap map, @PathVariable Integer num, HttpServletRequest request){
        Page<NewArticle> page = new Page<>();
        page.setPage(num);

        return index(map, page, request);
    }

}
