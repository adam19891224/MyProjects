package com.enjoylife.series.dao;

import com.enjoylife.series.vo.SeriesArticle;

public interface SeriesArticleMapper {
    int deleteByPrimaryKey(Integer seriesArticleId);

    int insert(SeriesArticle record);

    int insertSelective(SeriesArticle record);

    SeriesArticle selectByPrimaryKey(Integer seriesArticleId);

    int updateByPrimaryKeySelective(SeriesArticle record);

    int updateByPrimaryKey(SeriesArticle record);
}