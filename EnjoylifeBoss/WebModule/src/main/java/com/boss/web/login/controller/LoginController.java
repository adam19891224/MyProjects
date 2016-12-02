package com.boss.web.login.controller;

import com.boss.foundation.utils.StringUtils;
import com.boss.web.base.configuration.shiro.utils.LoginData;
import com.boss.web.base.configuration.shiro.utils.LoginToken;
import com.boss.web.base.controller.BaseController;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String doLogin(String userName, String password, ModelMap map){

        if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)){
            LoginData loginData = new LoginData.Builder().addUserName(userName).addPassword(password).build();
            Subject subject = getSubject();
            LoginToken token = new LoginToken(loginData);
            try {
                subject.login(token);
                return "success";
            }catch (RuntimeException e){
                logger.error("登陆异常", e);
                return e.getCause().getMessage();
            }
        }

        return "notBlank";
    }

    @RequestMapping("/logout.html")
    public String logout(ModelMap map){
        getSubject().logout();
        return "redirect:/login.html";
    }

    @RequestMapping("/noLogin.html")
    public String noLogin(ModelMap map){

        return "login/index";
    }

}
