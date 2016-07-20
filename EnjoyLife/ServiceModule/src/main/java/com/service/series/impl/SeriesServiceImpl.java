package com.service.series.impl;

import com.series.dao.SeriesMapper;
import com.series.vo.Series;
import com.series.vo.SeriesInfo;
import com.service.base.BaseAbstractClass;
import com.service.series.ISeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * adam
 * 2016/7/20
 */
@Service
public class SeriesServiceImpl extends BaseAbstractClass implements ISeriesService {

    @Autowired
    private SeriesMapper seriesMapper;

    @Override
    public List<SeriesInfo> selectAllSeries() {
        return seriesMapper.selectAllSeries();
    }
}
