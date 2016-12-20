package com.boss.dao.series.mapper;

import com.boss.dao.series.pojo.ArticleSeries;

public interface ArticleSeriesMapper {
    int deleteByPrimaryKey(Integer articleSeriesId);

    int insert(ArticleSeries record);

    int insertSelective(ArticleSeries record);

    ArticleSeries selectByPrimaryKey(Integer articleSeriesId);

    int updateByPrimaryKeySelective(ArticleSeries record);

    int updateByPrimaryKey(ArticleSeries record);
}