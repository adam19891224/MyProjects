package com.boss.web.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ranmin-zhouyuhong
 * 2016/12/12
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String index(ModelMap map){
        return "index/index";
    }

}
