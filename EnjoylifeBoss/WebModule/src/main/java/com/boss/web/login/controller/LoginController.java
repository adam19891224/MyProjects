package com.boss.web.login.controller;

import com.boss.foundation.utils.SessionStaticNameUtils;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
@Controller
public class LoginController extends BaseController{

    @RequestMapping("/login.html")
    public String login(ModelMap map){

        return "login/index";
    }

    @RequestMapping("/doLogin.html")
    public String doLogin(ModelMap map){

        return "login/index";
    }

    @RequestMapping("/logout.html")
    public String logout(ModelMap map){

        getSession().removeAttribute(SessionStaticNameUtils.SESSION_USER_INFO);

        return "redirect:/login.html";
    }

    @RequestMapping("/noLogin.html")
    public String noLogin(ModelMap map){

        return "login/index";
    }

}
