package com.foundation.view;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2015/12/22
 */
public class Page<T> implements Serializable {

    private String kw;

    private Integer page = 1;

    private Integer pageNum = 0;

    private Integer pageSize = 10;

    private String articleId;

    private List<T> resultList;

    private T resultObject;

    private Integer totalCounts;

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
}
