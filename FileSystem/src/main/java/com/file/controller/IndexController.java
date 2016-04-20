package com.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/5
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String index(){
        return "main";
    }

}
