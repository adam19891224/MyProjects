package com.web.controller;

import com.service.type.ITypeService;
import com.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/2
 */
@Controller
@RequestMapping("/types")
public class TypesController extends BaseController {

    @Resource
    private ITypeService typeService;

    @RequestMapping("/list.html")
    public String list(){
        logger.info("进入获取类型列表方法");
        List<Type> list = typeService.selectAllTypes();
        logger.info("退出获取类型列表方法");
        return super.parseObjectToJson(list);
    }

}
