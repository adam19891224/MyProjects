package com.boss.dao.blog.pojo;

import java.util.Date;

public class Article {
    private Integer articleSid;

    private String articleId;

    private Date createDate;

    private Date updateDate;

    private String articleTitle;

    private String articleBackup1;

    private String articleBackup2;

    private String articleBackup3;

    private String articleImg;

    public Integer getArticleSid() {
        return articleSid;
    }

    public void setArticleSid(Integer articleSid) {
        this.articleSid = articleSid;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleBackup1() {
        return articleBackup1;
    }

    public void setArticleBackup1(String articleBackup1) {
        this.articleBackup1 = articleBackup1 == null ? null : articleBackup1.trim();
    }

    public String getArticleBackup2() {
        return articleBackup2;
    }

    public void setArticleBackup2(String articleBackup2) {
        this.articleBackup2 = articleBackup2 == null ? null : articleBackup2.trim();
    }

    public String getArticleBackup3() {
        return articleBackup3;
    }

    public void setArticleBackup3(String articleBackup3) {
        this.articleBackup3 = articleBackup3 == null ? null : articleBackup3.trim();
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg == null ? null : articleImg.trim();
    }
}