package com.enjoylife.index.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.type.ITypeService;
import com.enjoylife.view.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private IBlogsService blogsService;
    @Resource
    private ITypeService typeService;

    /**
     * 首页方法
     * @param map
     */
    @RequestMapping("/index.html")
    public String index(ModelMap map, Page<NewArticle> page){

        page = blogsService.selectArticlesByPage(page);

        map.addAttribute("result", page.getResultList());
        map.addAttribute("totalCounts", page.getTotalCounts());
        map.addAttribute("totalPages", page.getTotalPages());
        map.addAttribute("page", page.getPage());
        map.addAttribute("isIndex", YesNoTypeEnum.Yes.getCode());

        //查询总分类数，给首页右边展示
        int types = typeService.selectAllTypesCount();
        map.addAttribute("allTypes", types);

        return "index/index";
    }

    @RequestMapping("/page/{num}.html")
    public String page(ModelMap map, @PathVariable Integer num){
        Page<NewArticle> page = new Page<>();
        page.setPage(num);
        return index(map, page);
    }

}
