package com.enjoylife.article.vo;

import java.util.List;

/**
 * Adam
 * 2016/8/2
 */
public class ArticleTime {

    private Integer date;

    private List<ArticleTime> list;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public List<ArticleTime> getList() {
        return list;
    }

    public void setList(List<ArticleTime> list) {
        this.list = list;
    }
}
