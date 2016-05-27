package com.foundation.enums;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/27
 */
public enum SolrResultMapKeyEnum {

    ResultSet("ResultSet", "结果集"),
    ResultCounts("ResultCounts", "总数量");

    SolrResultMapKeyEnum(String key, String message){
        this.key = key;
        this.message = message;
    }

    private String key;

    private String message;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
