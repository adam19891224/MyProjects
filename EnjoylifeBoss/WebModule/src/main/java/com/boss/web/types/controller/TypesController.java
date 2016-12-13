package com.boss.web.types.controller;

import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.view.Page;
import com.boss.service.types.TypesService;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
@Controller
@RequestMapping("/types")
public class TypesController extends BaseController{

    @Resource
    private TypesService typesService;

    @RequestMapping("/list.html")
    public String list(){

        return "types/list";
    }

    @RequestMapping("/datas.html")
    @ResponseBody
    public String datas(Page<TypesInfo> page){

        page = typesService.selectTypesByPage(page);
        return super.castPageToResultString(page);
    }

    @RequestMapping("/index.html")
    public String index(){
        return "types/index";
    }

}
