package com.enjoylife.index.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
     * @param map
     */
    @RequestMapping("/index.html")
    public String index(ModelMap map){
        map.addAttribute("isIndex", YesNoTypeEnum.Yes.getCode());
        return "index/index";
    }

    /**
     * 浏览器版本过低
     */
    @RequestMapping("/hope.html")
    public String hope(HttpServletRequest request){
        logger.info("来自： " + super.getIP(request) + "  的朋友由于本地浏览器版本过低， 进入提示页面          ------》");
        return "index/hope";
    }

}
