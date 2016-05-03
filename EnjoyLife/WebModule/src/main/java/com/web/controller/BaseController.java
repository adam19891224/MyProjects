package com.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.foundation.view.Page;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/15
 */
public class BaseController {

    Logger logger = Logger.getLogger("controllerLog");

    /**
     * 把参数转换为json
     */
    String parseObjectToJson(Object o){
        String res = null;
        try {
            res = JSONObject.toJSONString(o);
        }catch (Exception e){
            logger.error("JSON 转换错误： ");
            logger.error(e);
        }
        return res;
    }

    /**
     * xss过滤
     */
    protected <T> void escapeKeyword(Page<T> page){
        page.setKw(StringEscapeUtils.escapeJavaScript(page.getKw()));
    }

    HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }

}
