package com.web.controller;

import com.foundation.utils.ConUtils;
import com.service.blogs.IBlogsService;
import com.service.type.ITypeService;
import com.type.vo.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Adam
 * 2016/7/29
 */
@Controller
@RequestMapping("/eyes")
public class EyesController extends BaseController {

    @Resource
    private ITypeService typeService;
    @Resource
    private IBlogsService blogsService;

    /**
     * 进入一览页面
     * @return
     */
    @RequestMapping("/index.html")
    public String index(String kw, ModelMap map){

        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            logger.info("获取文章所有类型结果，结果数：" + types.size());
            map.addAttribute("types", types);
        }

        return "eyes/main";
    }
}
