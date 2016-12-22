package com.boss.web.types.controller;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.types.pojo.ArticleType;
import com.boss.dao.types.pojo.Type;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.utils.StringUtils;
import com.boss.foundation.view.Page;
import com.boss.service.blogs.IBlogService;
import com.boss.service.types.ITypesService;
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
    private ITypesService typesService;
    @Resource
    private IBlogService blogService;


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
            map.addAttribute("type", type);
            if(StringUtils.isNotBlank(type.getTypeParentId())){
                Type parentT = typesService.selectTypeById(type.getTypeParentId());
                map.addAttribute("parent", parentT);
            }
            List<Article> list = blogService.selectArticlesByTypeID(type.getTypeId());
            map.addAttribute("blogs", list);
        }

        return "types/manager";
    }

    @RequestMapping("/delete.html")
    @ResponseBody
    public String delete(Integer id){
        return typesService.deleteTypesSafeBySid(id);
    }

    @RequestMapping("/getArticles.html")
    @ResponseBody
    public String getArticles(String id){
        List<Article> list = blogService.selectArticlesWithOutTypeID(id);
        return super.castListToResultString(list);
    }

    @RequestMapping("/addArticle.html")
    @ResponseBody
    public String addArticle(ArticleType articleType){

        if(StringUtils.isNotBlank(articleType.getArticleId())
                && StringUtils.isNotBlank(articleType.getTypeId())){
            return typesService.addArticleByType(articleType);
        }
        return "null";
    }

    @RequestMapping("/deleteArticle.html")
    @ResponseBody
    public String deleteArticle(ArticleType articleType){

        if(StringUtils.isNotBlank(articleType.getArticleId())
                && StringUtils.isNotBlank(articleType.getTypeId())){
            return typesService.deleteArticleByType(articleType);
        }
        return "null";
    }
}
