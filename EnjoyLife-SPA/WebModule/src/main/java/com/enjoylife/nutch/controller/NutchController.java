package com.enjoylife.nutch.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsESService;
import com.enjoylife.modules.ArticleEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2017/2/9
 */
@Controller
public class NutchController extends BaseController{

    @Resource
    private IBlogsESService esService;

    @RequestMapping("/nutch")
    public String nutch(ModelMap map){
        List<ArticleEntity> list = esService.selectAllArticles();
        map.addAttribute("articles", list);
        return "nutch";
    }

}
