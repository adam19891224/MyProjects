package com.enjoylife.infomations.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2017/2/15
 */
@RestController
public class InfomationsController extends BaseController{

    @RequestMapping(value = "/infomations/tagcloud", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<Map<String, Object>> types(){
        Map<String, Object> map = ConUtils.hashmap();
        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.put("types", types);
        }
        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

    @RequestMapping(value = "/infomations/introduce", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<Map<String, Object>> totalInfo(){
        Map<String, Object> map = ConUtils.hashmap();
        int blogCount = articlesService.selectArticlesCountsByPage(new Page<>());
        map.put("totalArticles", blogCount);
        int size = typeService.selectAllTypesCount();
        map.put("totalTypes", size);
        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
