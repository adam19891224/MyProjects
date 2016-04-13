package com.web.controller;

import com.foundation.utils.IPUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@Controller
public class HomeController extends BaseController {

    @RequestMapping("/index.html")
    public String index(HttpServletRequest request){
        String ip = IPUtils.getClientIp(request);
        logger.info("来自： " + ip + "  的朋友访问本站！         ------》");
        return "home/home";
    }

}
