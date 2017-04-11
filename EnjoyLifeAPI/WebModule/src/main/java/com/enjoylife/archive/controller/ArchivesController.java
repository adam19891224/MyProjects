package com.enjoylife.archive.controller;

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
public class ArchivesController extends BaseController {

    @RequestMapping(value = "/archives/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> page(@PathVariable Integer num){
        Page<NewArticle> page = new Page<>();
        page.setPage(num);
        page = articlesService.selectArticlesByPage(page);

        Map<String, Object> map = ConUtils.hashmap();

        map.put("article", page.getResultList());
        //总页码
        map.put("totalPages", page.getTotalPages());
        //当前页码
        map.put("page", page.getPage());
        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
