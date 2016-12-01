package com.boss.web.base.configuration.shiro.utils;

/**
 * ranmin-zhouyuhong
 * 2016/11/14
 */
public class LoginData {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7358692269851431489L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 登录IP
     */
    private String loginIP;

    public static class Builder {

        private String userName;

        private String password;

        private String captcha;

        private String loginIP;
        //无参构造
        public Builder(){

        }
        public Builder addUserName(String name) {
            this.userName = name;
            return this;
        }
        public Builder addPassword(String password){
            this.password = password;
            return this;
        }
        public Builder addCaptcha(String captcha){
            this.captcha = captcha;
            return this;
        }
        public Builder addIP(String ip){
            this.loginIP = ip;
            return this;
        }

        //build方法，返回yishenghuologindata对象
        public LoginData build(){
            return new LoginData(this);
        }
    }

    //构造函数私有化，不允许通过new等方式创建
    private LoginData(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.captcha = builder.captcha;
        this.loginIP = builder.loginIP;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public String getLoginIP() {
        return loginIP;
    }
}
