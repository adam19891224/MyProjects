package com.boss.web.types.controller;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.types.pojo.Type;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.utils.StringUtils;
import com.boss.foundation.view.Page;
import com.boss.service.types.TypesService;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @RequestMapping("/getTypes.html")
    @ResponseBody
    public String getTypes(Page<TypesInfo> page){
        page = typesService.selectTypesByPage(page);
        return super.castPageToResultString(page);
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(Type type){
        try {
            return typesService.saveTypeInfo(type);
        }catch (Exception e){
            logger.error("保存类别失败: ", e);
        }
        return "error";
    }

    @RequestMapping("/manager.html")
    public String manager(Integer id, ModelMap map){
        if(StringUtils.isNotBlank(id.toString())){
            Type type = typesService.selectTypeBySid(id);
            if(type == null){
                map.addAttribute("null", "null");
                return "/error";
            }
            String tid = type.getTypeId();
            //根据typeid 查询现有的该类别下的所有文章
            List<Article> articles = typesService.selectArticlesByTypeID(tid);
        }

        return "types/manager";
    }
}
