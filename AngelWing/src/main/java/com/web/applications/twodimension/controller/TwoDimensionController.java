package com.web.applications.twodimension.controller;

import com.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/10
 */
@Controller
@RequestMapping("/applications/twodimension")
public class TwoDimensionController extends BaseController {

    @RequestMapping("/main")
    public String main(){
        return "applications/twodimension/main";
    }

    @RequestMapping("/weixinmain")
    public String weixinmain(){

        return "applications/twodimension/weixinmain";
    }

}
