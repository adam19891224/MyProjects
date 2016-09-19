package com.web.controller;

import com.article.vo.ArticleTime;
import com.article.vo.NewArticle;
import com.foundation.utils.ConUtils;
import com.foundation.view.Page;
import com.service.blogs.IBlogsService;
import com.service.type.ITypeService;
import com.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
        page = blogsService.selectArticlesByPageSolr(page);
        map.addAttribute("all", page);

        List<ArticleTime> times = blogsService.selectTimeGroupByArticle();
        map.addAttribute("times", times);

        return "eyes/main";
    }

    /**
     * 进入分类一览页
     */
    @RequestMapping("/category/{name}/{num}.html")
    public String category(ModelMap map, @PathVariable String name, @PathVariable Integer num){
        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
        }
        Page<NewArticle> page = new Page<NewArticle>();
        page.setTypeName(name);
        page.setPage(num);
        page = blogsService.selectArticlesByPage(page);
        map.addAttribute("all", page);

        return "";
    }
}
