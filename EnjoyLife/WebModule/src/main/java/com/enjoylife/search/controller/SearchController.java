package com.enjoylife.search.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Adam
 * 2016/9/29
 */
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {

    @Resource
    private ITypeService typeService;
    @Resource
    private IBlogsService blogsService;

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
        name = HtmlUtils.htmlEscape(name);
        page.setTypeName(name);
        page.setPage(num);
        page = blogsService.selectArticlesByPage(page);
        map.addAttribute("all", page);
        map.addAttribute("key", name);
        return "search/main";
    }

    /**
     * 进入搜索页
     */
    @RequestMapping("/keyword/{name}/{num}.html")
    public String keyword(ModelMap map, @PathVariable String name, @PathVariable Integer num){
        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
        }
        Page<NewArticle> page = new Page<NewArticle>();
        name = HtmlUtils.htmlEscape(name);
        page.setKw(name);
        page.setPage(num);
        page = blogsService.selectArticlesByPage(page);
        map.addAttribute("all", page);
        map.addAttribute("key", name);
        return "search/main";
    }
}
