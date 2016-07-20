package com.series.vo;

public class SeriesArticle {
    private Integer seriesArticleId;

    private String seriesId;

    private String articleId;

    public Integer getSeriesArticleId() {
        return seriesArticleId;
    }

    public void setSeriesArticleId(Integer seriesArticleId) {
        this.seriesArticleId = seriesArticleId;
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