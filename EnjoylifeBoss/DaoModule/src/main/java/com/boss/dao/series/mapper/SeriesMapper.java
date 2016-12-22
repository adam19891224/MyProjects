package com.boss.dao.series.mapper;

import com.boss.dao.series.pojo.ArticleSeries;
import com.boss.dao.series.pojo.Series;
import com.boss.dao.series.pojo.SeriesInfo;
import com.boss.foundation.view.Page;

import java.util.List;

public interface SeriesMapper {
    int deleteByPrimaryKey(Integer seriesSid);

    int insert(Series record);

    int insertSelective(Series record);

    Series selectByPrimaryKey(Integer seriesSid);

    int updateByPrimaryKeySelective(Series record);

    int updateByPrimaryKey(Series record);

    void deleteSeriesByArticleId(String id);

    List<SeriesInfo> selectSeriesByPage(Page<SeriesInfo> page);

    int selectSeriesCountByPage(Page<SeriesInfo> page);

    void deleteArticleBySeries(ArticleSeries articleSeries);

    void deleteArticleBySid(Integer id);
}