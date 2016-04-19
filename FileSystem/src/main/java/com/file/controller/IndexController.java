package com.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/5
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    @ResponseBody
    public String index(){
        return "你好!";
    }

}
