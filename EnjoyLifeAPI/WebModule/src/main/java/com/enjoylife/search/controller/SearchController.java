package com.enjoylife.search.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.blogs.IBlogsESService;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Adam
 * 2016/9/29
 */
@RestController
public class SearchController extends BaseController {

    @Resource
    private IBlogsESService esService;

    /**
     * 进入分类一览页
     */
    @RequestMapping(value = "/genre/{name}/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> genre(@PathVariable String name, @PathVariable Integer num){

        Map<String, Object> map = ConUtils.hashmap();

        Page<NewArticle> page = new Page<NewArticle>();
        name = HtmlUtils.htmlEscape(name);
        page.setTypeName(name);
        page.setPage(num);
        page = blogsService.selectTypeArticlesByPage(page);

        map.put("blogs", page.getResultList());
        map.put("totalPages", page.getTotalPages());
        map.put("page", num);

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

    /**
     * 进入搜索页
     */
    @RequestMapping(value = "/query/{name}/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> keyword(@PathVariable String name, @PathVariable Integer num){

        Map<String, Object> map = ConUtils.hashmap();

        Page<NewArticle> page = new Page<NewArticle>();
        //封装查询
        name = HtmlUtils.htmlEscape(name.trim());
        page.setKw(name);
        page.setEsPage(num);
        Map<String, Object> resM = esService.selectArticlesHighlightByPage(page);

        map.put("blogs", resM.get("result"));
        map.put("totalPages", resM.get("totalPage"));
        map.put("page", num);
        map.put("keyword", name);

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }
}
