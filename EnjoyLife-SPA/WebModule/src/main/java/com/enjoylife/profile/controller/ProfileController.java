package com.enjoylife.profile.controller;

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
 * 2016/11/28
 */
@RestController
public class ProfileController extends BaseController{

    @RequestMapping(value = "/profile", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> profile(){

        Map<String, Object> map = ConUtils.hashmap();

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
