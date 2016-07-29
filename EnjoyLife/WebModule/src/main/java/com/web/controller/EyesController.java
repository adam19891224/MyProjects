package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Adam
 * 2016/7/29
 */
@Controller
@RequestMapping("/eyes")
public class EyesController {

    /**
     * 进入一览页面
     * @return
     */
    @RequestMapping("/index.html")
    public String index(String kw, ModelMap map){

        return "eyes/main";
    }
}
