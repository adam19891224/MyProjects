package com.enjoylife.form;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/26
 */
public class CommentForm {

    private String articleId;

    private String commentUser;

    private String commentUserWebsite;

    private String commentEmail;

    private Integer commentPoint;

    private Byte commentIsReply;

    private String commentReplyUser;

    private String commentBody;

    private String commentReplyBody;

    private String commentId;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentReplyBody() {
        return commentReplyBody;
    }

    public void setCommentReplyBody(String commentReplyBody) {
        this.commentReplyBody = commentReplyBody;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentUserWebsite() {
        return commentUserWebsite;
    }

    public void setCommentUserWebsite(String commentUserWebsite) {
        this.commentUserWebsite = commentUserWebsite;
    }

    public String getCommentEmail() {
        return commentEmail;
    }

    public void setCommentEmail(String commentEmail) {
        this.commentEmail = commentEmail;
    }

    public Integer getCommentPoint() {
        return commentPoint;
    }

    public void setCommentPoint(Integer commentPoint) {
        this.commentPoint = commentPoint;
    }

    public Byte getCommentIsReply() {
        return commentIsReply;
    }

    public void setCommentIsReply(Byte commentIsReply) {
        this.commentIsReply = commentIsReply;
    }

    public String getCommentReplyUser() {
        return commentReplyUser;
    }

    public void setCommentReplyUser(String commentReplyUser) {
        this.commentReplyUser = commentReplyUser;
    }
}
