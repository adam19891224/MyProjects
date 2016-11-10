package com.enjoylife.eyes.controller;

import com.enjoylife.article.vo.ArticleTime;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Adam
 * 2016/7/29
 */
@Controller
@RequestMapping("/eyes")
public class EyesController extends BaseController {

    @Resource
    private ITypeService typeService;
    @Resource
    private IBlogsService blogsService;

    /**
     * 进入一览页面
     * @return
     */
    @RequestMapping("/index.html")
    public String index(Page<NewArticle> page, ModelMap map){

        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
        }

        //不分页
        page.setPagination(false);
        page = blogsService.selectArticlesByPage(page);
        map.addAttribute("all", page);

        List<ArticleTime> times = blogsService.selectTimeGroupByArticle();
        map.addAttribute("times", times);

        return "eyes/main";
    }

}
