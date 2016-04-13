package com.web.applications.article.controller;

import com.alibaba.fastjson.JSON;
import com.system.annotation.ControllerLogs;
import com.system.webui.Page;
import com.system.webui.Tree;
import com.tools.utils.ConUtils;
import com.web.applications.article.entity.Type;
import com.web.applications.article.service.TypeService;
import com.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/19
 */
@Controller
@RequestMapping("/applications/article/type")
public class ArticleTypeController extends BaseController {

    @Resource
    private TypeService typeService;

    private Page<Type> pages = new Page<>();

    @RequestMapping("/list")
    @ControllerLogs(description = "博客类目列表页")
    public String list(){
        return "applications/articletype/list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Type type){
        logger.info("进入 保存博客类目方法  ");
        try {
            if(type.getTypeParentId().equalsIgnoreCase("000"))
                type.setTypeParentId(null);
            typeService.insertType(type);
        }catch (Exception e){
            logger.error("保存博客类目 异常： " + e);
            return "error";
        }
        return "success";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Type type){
        logger.info("进入 修改博客类目方法  ");
        try {
            typeService.updateByPrimaryKeySelective(type);
        }catch (Exception e){
            logger.error("修改博客类目 异常： " + e);
            return "error";
        }
        return "success";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String id){
        logger.info("进入 修改博客类目方法  ");
        try {
            typeService.deleteByPrimarkId(id);
        }catch (Exception e){
            logger.error("修改博客类目 异常： " + e);
            return "error";
        }
        return "success";
    }

    @RequestMapping("/getJson")
    @ResponseBody
    public Object getJson(){
        List<Tree> list = typeService.selectToTree();
        for(Tree t : list){
            t.setParentId("000");
        }
        Tree root = new Tree();
        root.setId("000");
        root.setText("所有类别");
        root.setChildren(list);
        List<Tree> rootList = ConUtils.arraylist();
        rootList.add(root);
        return JSON.toJSON(rootList);
    }

}
