package com.enjoylife.series.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.series.ISeriesService;
import com.enjoylife.series.vo.SeriesInfo;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/29
 */
@RestController
public class SeriesController extends BaseController {

    @Resource
    private ISeriesService seriesService;


    /**
     * 专题首页方法
     */
    @RequestMapping(value = "/series", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> index(){

        Map<String, Object> map = ConUtils.hashmap();

        List<SeriesInfo> series = seriesService.selectAllSeries();
        map.put("series", series);

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
