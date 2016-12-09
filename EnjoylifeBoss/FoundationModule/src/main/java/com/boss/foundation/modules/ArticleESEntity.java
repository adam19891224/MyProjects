package com.boss.foundation.modules;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * ranmin-zhouyuhong
 *
 * 2016/11/22
 */
@Document(indexName = "enjoylife-articles", type = "articles", shards = 1, replicas = 0, refreshInterval = "-1")
public class ArticleESEntity implements Serializable {

    private static final long serialVersionUID = 5193625143787230749L;

    @Id
    private Integer articleSid;

    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed)
    private Date createDate;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String articleImg;

    @Field(type = FieldType.String, store = true, analyzer = "ik_max_word", includeInParent = true)
    private String articleTitle;

    @Field(type = FieldType.String, store = true, analyzer = "ik_max_word", includeInParent = true)
    private String articleDescription;

    public Integer getArticleSid() {
        return articleSid;
    }

    public void setArticleSid(Integer articleSid) {
        this.articleSid = articleSid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }
}
