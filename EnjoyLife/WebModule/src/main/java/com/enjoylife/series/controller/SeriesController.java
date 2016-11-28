package com.enjoylife.series.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.series.ISeriesService;
import com.enjoylife.series.vo.SeriesInfo;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
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

        map.addAttribute("isSeries", YesNoTypeEnum.Yes.getCode());

        List<SeriesInfo> series = seriesService.selectAllSeries();
        map.addAttribute("series", series);

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

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
