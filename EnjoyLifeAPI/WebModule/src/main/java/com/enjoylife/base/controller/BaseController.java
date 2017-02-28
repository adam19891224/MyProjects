package com.enjoylife.base.controller;

import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.type.ITypeService;
import com.enjoylife.utils.IPUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
     * 返回responseDatA数据
     */
    protected ResponseData<Map<String, Object>> responseRes(ResponseEnum responseEnum, Map<String, Object> map){
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        responseData.setCode(responseEnum.getCode());
        responseData.setMessage(responseEnum.getMessage());
        responseData.setData(map);
        return responseData;
    }
}
