package com.enjoylife.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.vo.Type;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.IPUtils;
import com.enjoylife.view.Page;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/15
 */
public class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass());

    @Resource
    protected ITypeService typeService;
    @Resource
    protected IBlogsService blogsService;

    /**
     * 把参数转换为json
     */
    protected String parseObjectToJson(Object o){
        String res = null;
        try {
            res = JSONObject.toJSONString(o);
        }catch (Exception e){
            logger.error("JSON 转换错误: =========================");
            logger.error("异常原因： " + e);
        }
        return res;
    }

    /**
     * xss过滤
     */
    protected <T> void escapeKeyword(Page<T> page){
        page.setKw(StringEscapeUtils.escapeJavaScript(page.getKw()));
    }

    /**
     * 获取session
     */
    protected HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }

    /**
     * 获取当前用户ip
     */
    protected String getIP(HttpServletRequest request){
        return IPUtils.getClientIp(request);
    }

    /**
     * 获取当前登录时的文章总数
     */
    protected void getTotalArticlesToMap(ModelMap map){
        int blogCount = blogsService.selectArticlesCountsByPage(new Page<NewArticle>());
        map.addAttribute("totalArticles", blogCount);
    }

    /**
     * 获取当前登录的类别总数
     */
    protected void getTotalTypesToMap(ModelMap map){
        List<Type> types = typeService.selectAllTypes();
        if(ConUtils.isNotNull(types)){
            map.addAttribute("types", types);
            map.addAttribute("totalTypes", types.size());
        }
    }
}
