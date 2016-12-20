package com.boss.dao.comment.pojo;

import java.util.Date;

public class Comment {
    private Integer commentSid;

    private String commentId;

    private String commentUser;

    private String commentUserWebsite;

    private String commentEmail;

    private Integer commentPoint;

    private Byte commentIsReply;

    private String commentReplyUser;

    private String commentReplyBody;

    private Date createDate;

    private Date updateDate;

    private String commentBackup1;

    private String commentBackup2;

    private String commentBackup3;

    private String commentBody;

    public Integer getCommentSid() {
        return commentSid;
    }

    public void setCommentSid(Integer commentSid) {
        this.commentSid = commentSid;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser == null ? null : commentUser.trim();
    }

    public String getCommentUserWebsite() {
        return commentUserWebsite;
    }

    public void setCommentUserWebsite(String commentUserWebsite) {
        this.commentUserWebsite = commentUserWebsite == null ? null : commentUserWebsite.trim();
    }

    public String getCommentEmail() {
        return commentEmail;
    }

    public void setCommentEmail(String commentEmail) {
        this.commentEmail = commentEmail == null ? null : commentEmail.trim();
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
        this.commentReplyUser = commentReplyUser == null ? null : commentReplyUser.trim();
    }

    public String getCommentReplyBody() {
        return commentReplyBody;
    }

    public void setCommentReplyBody(String commentReplyBody) {
        this.commentReplyBody = commentReplyBody == null ? null : commentReplyBody.trim();
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

    public String getCommentBackup1() {
        return commentBackup1;
    }

    public void setCommentBackup1(String commentBackup1) {
        this.commentBackup1 = commentBackup1 == null ? null : commentBackup1.trim();
    }

    public String getCommentBackup2() {
        return commentBackup2;
    }

    public void setCommentBackup2(String commentBackup2) {
        this.commentBackup2 = commentBackup2 == null ? null : commentBackup2.trim();
    }

    public String getCommentBackup3() {
        return commentBackup3;
    }

    public void setCommentBackup3(String commentBackup3) {
        this.commentBackup3 = commentBackup3 == null ? null : commentBackup3.trim();
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody == null ? null : commentBody.trim();
    }
}