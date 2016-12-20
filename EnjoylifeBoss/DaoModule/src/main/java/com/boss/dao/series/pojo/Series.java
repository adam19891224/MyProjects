package com.boss.dao.series.pojo;

import java.util.Date;

public class Series {
    private Integer seriesSid;

    private String seriesName;

    private String seriesId;

    private Date createTime;

    private Date updateTime;

    public Integer getSeriesSid() {
        return seriesSid;
    }

    public void setSeriesSid(Integer seriesSid) {
        this.seriesSid = seriesSid;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName == null ? null : seriesName.trim();
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId == null ? null : seriesId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}