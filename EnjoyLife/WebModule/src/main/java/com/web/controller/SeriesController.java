package com.web.controller;

import com.foundation.utils.ConUtils;
import com.series.vo.Series;
import com.series.vo.SeriesInfo;
import com.service.series.ISeriesService;
import com.service.type.ITypeService;
import com.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/29
 */
@Controller
@RequestMapping("/series")
public class SeriesController extends BaseController {

    @Resource
    private ITypeService typeService;
    @Resource
    private ISeriesService seriesService;


    /**
     * 专题首页方法
     * @param map
     * @return
     */
    @RequestMapping("/index.html")
    public String index(ModelMap map){

        logger.info("进入专题系列首页");

        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            logger.info("获取文章所有类型结果，结果数：" + types.size());
            map.addAttribute("types", types);
        }

        List<SeriesInfo> series = seriesService.selectAllSeries();
        map.addAttribute("series", series);

        return "series/main";
    }

}
