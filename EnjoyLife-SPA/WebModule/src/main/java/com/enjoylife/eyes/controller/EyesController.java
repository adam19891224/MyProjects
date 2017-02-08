package com.enjoylife.eyes.controller;

import com.enjoylife.article.vo.ArticleTime;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.view.Page;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Adam
 * 2016/7/29
 */
@RestController
public class EyesController extends BaseController {

    /**
     * 进入一览页面
     * @return
     */
    @RequestMapping(value = "/eyes", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Page<NewArticle> page, ModelMap map, HttpServletRequest request){

        map.addAttribute("isEyes", YesNoTypeEnum.Yes.getCode());

        //不分页
        page.setPagination(false);
        page = blogsService.selectArticlesByPage(page);
        map.addAttribute("result", page.getResultList());

        List<ArticleTime> times = blogsService.selectTimeGroupByArticle();
        map.addAttribute("times", times);

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        map.addAttribute("dataType", "eyes");

        try {
            return toPjax(request, map, "eyes");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误", e);
        }

        return "/error";
    }

}
