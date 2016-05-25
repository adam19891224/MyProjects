package com.foundation.view;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/22
 */
public class Page<T> implements Serializable {

    Logger logger = Logger.getLogger("controllerLog");

    private String kw;

    private Integer page = 1;

    private Integer pageNum = 0;

    private Integer pageSize = 20;

    private String articleId;

    private List<T> resultList;

    private T resultObject;

    private Integer totalCounts;

    private Integer totalPages = 1;

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
