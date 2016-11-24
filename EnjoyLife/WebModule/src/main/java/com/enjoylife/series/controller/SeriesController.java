package com.enjoylife.series.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.series.vo.SeriesInfo;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.series.ISeriesService;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
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

        map.addAttribute("isSeries", YesNoTypeEnum.Yes.getCode());

        //查询总文章数和总分类数给前台展示
        int typesCount = typeService.selectAllTypesCount();
        map.addAttribute("allTypes", typesCount);

        int blogCount = blogsService.selectArticlesCountsByPage(new Page<NewArticle>());
        map.addAttribute("totalCounts", blogCount);

        return "series/index";
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
        return "search/index";
    }

}
