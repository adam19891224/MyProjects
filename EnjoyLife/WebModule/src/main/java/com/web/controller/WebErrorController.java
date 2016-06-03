package com.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/14
 */
@Controller
public class WebErrorController extends BaseController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public String handleError(HttpServletRequest request){
        logger.error("来自： " + super.getIP(request) + "  的朋友访问出现404! ");
        return "error/main";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
