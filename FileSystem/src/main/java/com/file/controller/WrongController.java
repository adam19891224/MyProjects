package com.file.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/20
 */
@Controller
public class WrongController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


    @RequestMapping(value = ERROR_PATH)
    public String error(){
        return "main";
    }

}
