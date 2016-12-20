package com.boss.dao.series.pojo;

public class ArticleSeries {
    private Integer articleSeriesId;

    private String seriesId;

    private String articleId;

    public Integer getArticleSeriesId() {
        return articleSeriesId;
    }

    public void setArticleSeriesId(Integer articleSeriesId) {
        this.articleSeriesId = articleSeriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId == null ? null : seriesId.trim();
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }
}