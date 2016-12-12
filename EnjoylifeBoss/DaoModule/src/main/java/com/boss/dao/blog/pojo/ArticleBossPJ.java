package com.boss.dao.blog.pojo;

/**
 * ranmin-zhouyuhong
 * 2016/12/12
 */
public class ArticleBossPJ extends ArticleWithBLOBs {

    private Integer commentCounts;

    public Integer getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
    }
}
