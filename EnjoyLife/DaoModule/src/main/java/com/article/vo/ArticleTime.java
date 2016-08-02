package com.article.vo;

import java.util.List;

/**
 * Adam
 * 2016/8/2
 */
public class ArticleTime {

    private String date;

    private List<ArticleTime> list;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ArticleTime> getList() {
        return list;
    }

    public void setList(List<ArticleTime> list) {
        this.list = list;
    }
}
