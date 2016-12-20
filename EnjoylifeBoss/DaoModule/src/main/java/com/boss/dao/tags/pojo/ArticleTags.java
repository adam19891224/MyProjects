package com.boss.dao.tags.pojo;

public class ArticleTags {
    private Integer tagArticleId;

    private String tagId;

    private String articleId;

    public Integer getTagArticleId() {
        return tagArticleId;
    }

    public void setTagArticleId(Integer tagArticleId) {
        this.tagArticleId = tagArticleId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }
}