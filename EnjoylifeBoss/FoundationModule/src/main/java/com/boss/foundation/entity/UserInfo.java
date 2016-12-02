package com.boss.foundation.entity;

import java.io.Serializable;

/**
 * ranmin-zhouyuhong
 * 2016/12/2
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8149722906228431129L;

    private Integer userSid;

    private String userId;

    private String userName;

    private String userPassword;

    private String userSimpleName;

    private String userRole;

    public Integer getUserSid() {
        return userSid;
    }

    public void setUserSid(Integer userSid) {
        this.userSid = userSid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSimpleName() {
        return userSimpleName;
    }

    public void setUserSimpleName(String userSimpleName) {
        this.userSimpleName = userSimpleName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
