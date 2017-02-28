package com.enjoylife.series.impl;

import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.series.ISeriesService;
import com.enjoylife.series.dao.SeriesMapper;
import com.enjoylife.series.vo.SeriesInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * adam
 * 2016/7/20
 */
@Service
public class SeriesServiceImpl extends BaseAbstractClass implements ISeriesService {

    @Resource
    private SeriesMapper seriesMapper;

    @Override
    public List<SeriesInfo> selectAllSeries() {
        return seriesMapper.selectAllSeries();
    }
}
