package com.web.controller;

import com.service.blogs.IBlogsService;
import com.service.type.ITypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/3
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController extends BaseController {

    @Resource
    private IBlogsService blogsService;
    @Resource
    private ITypeService typeService;

    @RequestMapping("/page.html")
    public String page(){
        return "calendar/";
    }

}
