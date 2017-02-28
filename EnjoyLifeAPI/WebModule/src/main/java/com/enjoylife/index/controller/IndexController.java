package com.enjoylife.index.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@RestController
public class IndexController extends BaseController {

    /**
     * 获取数据
     */
    @RequestMapping(value = {"/index"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<Map<String, Object>> index(Page<NewArticle> page){

        page = blogsService.selectArticlesByPage(page);
        Map<String, Object> map = ConUtils.hashmap();
        map.put("blogs", page.getResultList());
        //总页码
        map.put("totalPages", page.getTotalPages());
        //当前页码
        map.put("page", page.getPage());

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

    @RequestMapping(value = "/index/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> page(@PathVariable Integer num){
        Page<NewArticle> page = new Page<>();
        page.setPage(num);
        return index(page);
    }

}
