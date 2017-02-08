package com.enjoylife.enums;

/**
 * Adam
 * 2016/11/10
 */
public enum ResponseEnum {

    SUCCESS("Y", "响应成功"),
    ERROR("N", "响应失败");

    private String code;

    private String message;

    private ResponseEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
