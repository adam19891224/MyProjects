package com.foundation.enums;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/21
 */
public enum UserEnum {

    LoginUser("zyh", "5F292F114300FBC3EB73E280463408FA");

    private String name;

    private String password;

    private UserEnum(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

}
