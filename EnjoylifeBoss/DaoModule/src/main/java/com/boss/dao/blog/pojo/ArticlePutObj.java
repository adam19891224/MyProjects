package com.boss.dao.blog.pojo;

import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/12/21
 */
public class ArticlePutObj {

    private String articleId;

    private List<String> tagIds;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}
