package com.boss.dao.series.mapper;

import com.boss.dao.series.pojo.Series;

public interface SeriesMapper {
    int deleteByPrimaryKey(Integer seriesSid);

    int insert(Series record);

    int insertSelective(Series record);

    Series selectByPrimaryKey(Integer seriesSid);

    int updateByPrimaryKeySelective(Series record);

    int updateByPrimaryKey(Series record);

    void deleteSeriesByArticleId(String id);
}