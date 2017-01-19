package com.boss.dao.friends.pojo;

import java.util.Date;

public class Friends {
    private Integer friendSid;

    private String friendId;

    private String friendName;

    private String friendValue;

    private String friendTips;

    private Date createDate;

    private Date updateDate;

    public Integer getFriendSid() {
        return friendSid;
    }

    public void setFriendSid(Integer friendSid) {
        this.friendSid = friendSid;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId == null ? null : friendId.trim();
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName == null ? null : friendName.trim();
    }

    public String getFriendValue() {
        return friendValue;
    }

    public void setFriendValue(String friendValue) {
        this.friendValue = friendValue == null ? null : friendValue.trim();
    }

    public String getFriendTips() {
        return friendTips;
    }

    public void setFriendTips(String friendTips) {
        this.friendTips = friendTips == null ? null : friendTips.trim();
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
}