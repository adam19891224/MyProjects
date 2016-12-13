package com.boss.foundation.view;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/22
 */
public class Page<T> implements Serializable {

    /**
     * 分页参数
     */
    private Integer page = 1;

    private Integer pageNum = 0;

    private Integer pageSize = 10;

    private Integer totalCounts;

    private Integer totalPages = 1;

    private Boolean pagination = true;

    private Boolean success = false;

    /**
     * 搜索关键字
     */
    private String kw;

    private Integer esPage = 0;

    /**
     * 文章相关属性
     */
    private String articleId;

    private Byte isReply;

    /**
     * 类别相关属性
     */
    private Integer typeSid;

    private String typeId;

    private String typeName;

    private String typeParentId;

    /**
     * 系列相关属性
     */
    private String seriesName;

    private Integer seriesSid;

    private String seriesId;

    /**
     * 评论相关
     */
    private String commentId;

    private Byte commentIsReply;

    /**
     * 返回结果集
     */
    private List<T> resultList;

    private T resultObject;

    public Integer getEsPage() {
        return esPage;
    }

    public void setEsPage(Integer esPage) {
        if(esPage > 0) esPage--;
        if(esPage <= 0) esPage = 0;
        this.esPage = esPage;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Byte getCommentIsReply() {
        return commentIsReply;
    }

    public void setCommentIsReply(Byte commentIsReply) {
        this.commentIsReply = commentIsReply;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Integer getSeriesSid() {
        return seriesSid;
    }

    public void setSeriesSid(Integer seriesSid) {
        this.seriesSid = seriesSid;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public Boolean getPagination() {
        return pagination;
    }

    public void setPagination(Boolean pagination) {
        this.pagination = pagination;
    }

    public Integer getTypeSid() {
        return typeSid;
    }

    public void setTypeSid(Integer typeSid) {
        this.typeSid = typeSid;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeParentId() {
        return typeParentId;
    }

    public void setTypeParentId(String typeParentId) {
        this.typeParentId = typeParentId;
    }

    public Byte getIsReply() {
        return isReply;
    }

    public void setIsReply(Byte isReply) {
        this.isReply = isReply;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
        this.totalPages = (totalCounts + this.pageSize - 1) / this.pageSize;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        this.setPageNum(page);
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public T getResultObject() {
        return resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.setPageNum(this.page);
    }

    public Integer getPageNum() {
        return pageNum;
    }

    private void setPageNum(Integer pageNum) {
        this.pageNum = pageNum > 1 ? (pageNum - 1) * pageSize : 0;
    }

    @Override
    public String toString() {
        return "【page】对象信息: " +
                "当前页: " + (this.page - 1) +
                "  下一页: " + this.page +
                "  下一页记录起始位置: " + this.pageNum +
                "  每一页显示记录数: " + pageSize;
    }

}
