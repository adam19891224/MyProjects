package com.service.series;

import com.series.vo.Series;
import com.series.vo.SeriesInfo;

import java.util.List;

/**
 * adam
 * 2016/7/20
 */
public interface ISeriesService {

    List<SeriesInfo> selectAllSeries();

}
