package com.boss.dao.tags.pojo;

public class Tags {
    private Integer tagSid;

    private String tagId;

    private String tagName;

    public Integer getTagSid() {
        return tagSid;
    }

    public void setTagSid(Integer tagSid) {
        this.tagSid = tagSid;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }
}