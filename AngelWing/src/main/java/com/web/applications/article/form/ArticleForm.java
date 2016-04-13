package com.web.applications.article.form;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/30
 */
public class ArticleForm {

    private String articleTitle;

    private String articleDesc;

    private String articleImage;

    private String articleBody;

    private String typeID;

    private String noParent;

    private String parentId;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getNoParent() {
        return noParent;
    }

    public void setNoParent(String noParent) {
        this.noParent = noParent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
