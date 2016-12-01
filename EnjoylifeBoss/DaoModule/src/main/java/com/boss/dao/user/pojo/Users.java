package com.boss.dao.user.pojo;

public class Users {
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
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserSimpleName() {
        return userSimpleName;
    }

    public void setUserSimpleName(String userSimpleName) {
        this.userSimpleName = userSimpleName == null ? null : userSimpleName.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }
}