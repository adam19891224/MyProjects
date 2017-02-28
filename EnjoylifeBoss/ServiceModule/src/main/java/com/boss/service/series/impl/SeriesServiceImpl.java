package com.boss.service.series.impl;

import com.boss.dao.blog.mapper.ArticleMapper;
import com.boss.dao.series.mapper.ArticleSeriesMapper;
import com.boss.dao.series.mapper.SeriesMapper;
import com.boss.dao.series.pojo.ArticleSeries;
import com.boss.dao.series.pojo.Series;
import com.boss.dao.series.pojo.SeriesInfo;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.view.Page;
import com.boss.service.base.AbstractService;
import com.boss.service.series.ISeriesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ranmin-zhouyuhong
 * 2016/12/22
 */
@Service
public class SeriesServiceImpl extends AbstractService implements ISeriesService {

    @Resource
    private SeriesMapper seriesMapper;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleSeriesMapper articleSeriesMapper;

    @Override
    public Page<SeriesInfo> selectSeriesByPage(Page<SeriesInfo> page) {
        List<SeriesInfo> result = seriesMapper.selectSeriesByPage(page);
        if(ConUtils.isNotNull(result)){
            int count = seriesMapper.selectSeriesCountByPage(page);
            page.setTotalCounts(count);
            page.setResultList(result);
            page.setSuccess(true);
        }
        return page;
    }

    @Override
    public String insertSeries(Series series) {
        series.setSeriesId(UUID.randomUUID().toString());
        series.setCreateTime(new Date());
        series.setUpdateTime(new Date());
        seriesMapper.insertSelective(series);
        return "success";
    }

    @Override
    public Series selectSeriesByPrimaryKey(Integer id) {
        return seriesMapper.selectByPrimaryKey(id);
    }

    @Override
    public String addArticleBySeries(ArticleSeries articleSeries) {
        try {
            articleSeriesMapper.insertSelective(articleSeries);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }
        return "error";
    }

    @Override
    public String deleteArticleBySeries(ArticleSeries articleSeries) {
        try {
            seriesMapper.deleteArticleBySeries(articleSeries);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }
        return "error";
    }

    @Override
    @Transactional
    public String deleteSeriesSafeBySid(Integer id) {
        try {
            seriesMapper.deleteArticleBySid(id);
            seriesMapper.deleteByPrimaryKey(id);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }

        return "error";
    }

}
