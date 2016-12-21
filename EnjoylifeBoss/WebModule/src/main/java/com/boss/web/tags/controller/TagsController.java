package com.boss.web.tags.controller;

import com.boss.dao.tags.pojo.Tags;
import com.boss.dao.tags.pojo.TagsInfo;
import com.boss.foundation.view.Page;
import com.boss.service.tags.ITagsService;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ranmin-zhouyuhong
 * 2016/12/21
 */
@Controller
@RequestMapping("/tags")
public class TagsController extends BaseController{

    @Resource
    private ITagsService tagsService;

    @RequestMapping("/list.html")
    public String list(){
        return "tags/list";
    }

    @RequestMapping("/datas.html")
    @ResponseBody
    public String datas(Page<TagsInfo> page){
        page = tagsService.selectTagsByPage(page);
        return super.castPageToResultString(page);
    }

    @RequestMapping("/index.html")
    public String index(){
        return "tags/index";
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(Tags tags){
        try {
            return tagsService.saveTagInfo(tags);
        }catch (Exception e){
            logger.error("保存标签失败: ", e);
        }
        return "error";
    }

    @RequestMapping("/delete.html")
    @ResponseBody
    public String delete(Integer id){
        return tagsService.deleteTagSafeByTagSid(id);
    }
}
