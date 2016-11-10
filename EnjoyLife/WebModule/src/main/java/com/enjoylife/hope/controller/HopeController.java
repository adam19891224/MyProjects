package com.enjoylife.hope.controller;

import com.enjoylife.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by adam on 2016/11/10.
 */
@Controller
public class HopeController extends BaseController{

    /**
     * 浏览器版本过低
     */
    @RequestMapping("/hope.html")
    public String hope(HttpServletRequest request){
        logger.info("来自： " + super.getIP(request) + "  的朋友由于本地浏览器版本过低， 进入提示页面          ------》");
        return "index/hope";
    }

}
