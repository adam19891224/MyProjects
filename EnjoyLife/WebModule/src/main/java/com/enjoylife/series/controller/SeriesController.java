package com.enjoylife.series.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.series.ISeriesService;
import com.enjoylife.series.vo.SeriesInfo;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
     * @param map
     * @return
     */
    @RequestMapping(value = "/series", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(ModelMap map, HttpServletRequest request){

        map.addAttribute("isSeries", YesNoTypeEnum.Yes.getCode());

        List<SeriesInfo> series = seriesService.selectAllSeries();
        map.addAttribute("series", series);

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        map.addAttribute("dataType", "series");

        try {
            return toPjax(request, map, "series");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

}
