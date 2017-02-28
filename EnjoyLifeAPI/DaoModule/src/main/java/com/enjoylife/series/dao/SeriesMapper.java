package com.enjoylife.series.dao;

import com.enjoylife.series.vo.Series;
import com.enjoylife.series.vo.SeriesInfo;

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