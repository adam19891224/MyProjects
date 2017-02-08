package com.enjoylife.index.controller;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/13
 */
@RestController
public class IndexController extends BaseController {

    /**
     * 首页方法
     * @param map
     */
    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<Map<String, Object>> index(Page<NewArticle> page, HttpServletRequest request){

        page = blogsService.selectArticlesByPage(page);
        Map<String, Object> map = ConUtils.hashmap();
        map.put("result", page.getResultList());
        map.put("totalArticles", page.getTotalCounts());
        map.put("totalPages", page.getTotalPages());
        map.put("page", page.getPage());
        map.put("isIndex", YesNoTypeEnum.Yes.getCode());

        //首页
        map.put("dataType", "index");

        //查询分类数
        super.getTotalTypesToMap(map);
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        responseData.setCode("1");
        responseData.setMessage("成功");
        responseData.setData(map);
        return responseData;
//        try {
//            return toPjax(request, map, "index");
//        } catch (TemplateException | IOException e) {
//            logger.error("pjax返回错误");
//        }
//
//        return "/error";
    }

//    @RequestMapping(value = "/index/{num}", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelMap page(ModelMap map, @PathVariable Integer num, HttpServletRequest request){
//        Page<NewArticle> page = new Page<>();
//        page.setPage(num);
//
//        return index(map, page, request);
//    }

}
