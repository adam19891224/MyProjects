package com.enjoylife.eyes.controller;

import com.enjoylife.article.vo.ArticleTime;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Adam
 * 2016/7/29
 */
@RestController
public class EyesController extends BaseController {

    /**
     * 进入一览页面
     * @return
     */
    @RequestMapping(value = "/eyes", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> index(Page<NewArticle> page){

        Map<String, Object> map = ConUtils.hashmap();

        //不分页
        page.setPagination(false);
        page = blogsService.selectArticlesByPage(page);
        map.put("blogs", page.getResultList());

        List<ArticleTime> times = blogsService.selectTimeGroupByArticle();
        map.put("times", times);

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
