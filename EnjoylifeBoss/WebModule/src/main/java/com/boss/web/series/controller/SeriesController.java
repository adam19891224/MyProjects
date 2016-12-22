package com.boss.web.series.controller;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.series.pojo.ArticleSeries;
import com.boss.dao.series.pojo.Series;
import com.boss.dao.series.pojo.SeriesInfo;
import com.boss.foundation.utils.StringUtils;
import com.boss.foundation.view.Page;
import com.boss.service.blogs.IBlogService;
import com.boss.service.series.ISeriesService;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
@Controller
@RequestMapping("/series")
public class SeriesController extends BaseController{

    @Resource
    private ISeriesService seriesService;
    @Resource
    private IBlogService blogService;

    @RequestMapping("/list.html")
    public String list(){
        return "series/list";
    }

    @RequestMapping("/datas.html")
    @ResponseBody
    public String datas(Page<SeriesInfo> page){
        page = seriesService.selectSeriesByPage(page);
        return super.castPageToResultString(page);
    }

    @RequestMapping("/add.html")
    public String add(){
        return "series/index";
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(Series series){
        if(!StringUtils.isNotBlank(series.getSeriesName())){
            return "null";
        }
        return seriesService.insertSeries(series);
    }

    @RequestMapping("/manager.html")
    public String manager(Integer id, ModelMap map){

        Series series = seriesService.selectSeriesByPrimaryKey(id);
        if(series == null){
            return "/error";
        }
        map.addAttribute("series", series);
        String seriesId = series.getSeriesId();
        //根据该专题id获取所有该专题的文章
        List<Article> list = blogService.selectArticlesBySeriesId(seriesId);
        map.addAttribute("blogs", list);

        return "series/manager";
    }

    @RequestMapping("/getArticles.html")
    @ResponseBody
    public String getArticles(String id){
        List<Article> list = blogService.selectArticlesWithOutSeriesID(id);
        return super.castListToResultString(list);
    }

    @RequestMapping("/addArticle.html")
    @ResponseBody
    public String addArticle(ArticleSeries articleSeries){

        if(StringUtils.isNotBlank(articleSeries.getArticleId())
                && StringUtils.isNotBlank(articleSeries.getSeriesId())){
            return seriesService.addArticleBySeries(articleSeries);
        }
        return "null";
    }

    @RequestMapping("/deleteArticle.html")
    @ResponseBody
    public String deleteArticle(ArticleSeries articleSeries){

        if(StringUtils.isNotBlank(articleSeries.getArticleId())
                && StringUtils.isNotBlank(articleSeries.getSeriesId())){
            return seriesService.deleteArticleBySeries(articleSeries);
        }
        return "null";
    }

    @RequestMapping("/delete.html")
    @ResponseBody
    public String delete(Integer id){

        return seriesService.deleteSeriesSafeBySid(id);
    }
}
