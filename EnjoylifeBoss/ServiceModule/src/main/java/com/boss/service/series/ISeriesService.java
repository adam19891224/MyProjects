package com.boss.service.series;

import com.boss.dao.series.pojo.ArticleSeries;
import com.boss.dao.series.pojo.Series;
import com.boss.dao.series.pojo.SeriesInfo;
import com.boss.foundation.view.Page;

/**
 * ranmin-zhouyuhong
 * 2016/12/22
 */
public interface ISeriesService {

    Page<SeriesInfo> selectSeriesByPage(Page<SeriesInfo> page);

    String insertSeries(Series series);

    Series selectSeriesByPrimaryKey(Integer id);

    String addArticleBySeries(ArticleSeries articleSeries);

    String deleteArticleBySeries(ArticleSeries articleSeries);

    String deleteSeriesSafeBySid(Integer id);
}
