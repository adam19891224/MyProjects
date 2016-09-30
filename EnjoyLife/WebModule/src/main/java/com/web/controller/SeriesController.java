package com.web.controller;

import com.article.vo.NewArticle;
import com.foundation.utils.ConUtils;
import com.foundation.view.Page;
import com.series.vo.SeriesInfo;
import com.service.blogs.IBlogsService;
import com.service.series.ISeriesService;
import com.service.type.ITypeService;
import com.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Resource
    private IBlogsService blogsService;


    /**
     * 专题首页方法
     * @param map
     * @return
     */
    @RequestMapping("/index.html")
    public String index(ModelMap map){

        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
        }

        List<SeriesInfo> series = seriesService.selectAllSeries();
        map.addAttribute("series", series);

        return "series/main";
    }

    /**
     * 进入分类一览页
     */
    @RequestMapping("/{name}/{num}.html")
    public String category(ModelMap map, @PathVariable String name, @PathVariable Integer num){
        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
        }

        Page<NewArticle> page = new Page<NewArticle>();
        page.setSeriesName(name);
        page.setPage(num);
        page = blogsService.selectArticlesByPage(page);
        map.addAttribute("all", page);
        map.addAttribute("key", name);
        return "search/main";
    }

}
