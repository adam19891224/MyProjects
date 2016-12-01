package com.boss.web.base.configuration.shiro.utils;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * ranmin-zhouyuhong
 * 2016/11/14
 */
public class LoginToken extends UsernamePasswordToken {

    private LoginData loginData;

    private LoginData getLoginData() {
        return loginData;
    }

    public LoginToken(LoginData data){
        this(data, true, null);
    }

    public LoginToken(LoginData data, boolean isRemeberMe){
        this(data, isRemeberMe, null);
    }

    private LoginToken(LoginData data, boolean isRemeberMe, String host){
        //调用父类的构造器创建token对象，传入参数：用户名，密码，是否rememberMe, 主机
        super(data.getUserName(), data.getPassword() != null ? data.getPassword().toCharArray() : null, isRemeberMe);
        this.loginData = data;
    }
}
