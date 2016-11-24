package com.enjoylife.search.controller;

import com.enjoylife.article.vo.ArticleEntity;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsESService;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
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
    @Resource
    private IBlogsESService esService;

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

        map.addAttribute("isEyes", YesNoTypeEnum.Yes.getCode());

        Page<NewArticle> page = new Page<NewArticle>();
        //查询总文章数和总分类数给前台展示
        int typesCount = typeService.selectAllTypesCount();
        map.addAttribute("allTypes", typesCount);

        int blogCount = blogsService.selectArticlesCountsByPage(page);
        map.addAttribute("totalCounts", blogCount);

        //封装查询
        name = HtmlUtils.htmlEscape(name);
        page.setKw(name);
        page.setEsPage(num);
        List<ArticleEntity> results = esService.selectArticlesByPage(page);

        map.addAttribute("all", page);
        map.addAttribute("key", name);

//        Page<NewArticle> page = new Page<NewArticle>();
//        page.setPagination(false);
//        page = blogsService.selectArticlesByPage(page);
//
//        esService.insertArticlesByList(page.getResultList());
        return "search/main";
    }
}
