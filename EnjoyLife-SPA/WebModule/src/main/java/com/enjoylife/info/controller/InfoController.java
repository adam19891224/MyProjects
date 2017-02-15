package com.enjoylife.info.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2017/2/15
 */
@RestController
public class InfoController extends BaseController{

    @RequestMapping(value = "/infos/types", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<Map<String, Object>> types(){
        Map<String, Object> map = ConUtils.hashmap();
        super.getTotalTypesToMap(map);
        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

    @RequestMapping(value = "/infos/totalInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<Map<String, Object>> totalInfo(){
        Map<String, Object> map = ConUtils.hashmap();
        super.getTotalArticlesToMap(map);
        super.getTotalTypesCountToMap(map);
        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
