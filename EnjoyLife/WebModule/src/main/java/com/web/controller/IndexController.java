package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@Controller
public class IndexController extends BaseController {

    /**
     * 首页方法
     * @param request
     * @return
     */
    @RequestMapping("/index.html")
    public String index(HttpServletRequest request){
        logger.info("来自： " + super.getIP(request) + "  的朋友访问本站！         ------》");
        return "index/main";
    }

}
