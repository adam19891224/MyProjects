package com.series.dao;

import com.series.vo.Series;
import com.series.vo.SeriesInfo;

import java.util.List;

public interface SeriesMapper {
    int deleteByPrimaryKey(Integer seriesSid);

    int insert(Series record);

    int insertSelective(Series record);

    Series selectByPrimaryKey(Integer seriesSid);

    int updateByPrimaryKeySelective(Series record);

    int updateByPrimaryKey(Series record);

    List<SeriesInfo> selectAllSeries();
}