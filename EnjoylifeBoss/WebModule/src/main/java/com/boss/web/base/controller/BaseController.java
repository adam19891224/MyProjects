package com.boss.web.base.controller;

import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.utils.SessionKeyUtils;
import com.boss.foundation.view.Page;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
public abstract class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected static final String[] IMG_TYPE = {".jpg", ".png", ".gif", ".bmp"};

    private Gson gson = new Gson();

    protected Session getSession(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            return subject.getSession();
        }
        return null;
    }

    protected Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    protected UserInfo getUser(){
        return (UserInfo) this.getSession().getAttribute(SessionKeyUtils.SESSION_USER_INFO);
    }

    protected String toJsonString(Object o){
        return gson.toJson(o);
    }

    protected <T> String  castPageToResultString(Page<T> page){
        Map<String, Object> resultMap = ConUtils.hashmap();
        resultMap.put("code", "0");
        if(page.getSuccess()){
            resultMap.put("code", "1");
            resultMap.put("totalCount", page.getTotalCounts());
            resultMap.put("totalPages", page.getTotalPages());
            resultMap.put("datas", page.getResultList());
        }
        return this.toJsonString(resultMap);
    }

    protected <T> String  castListToResultString(List<T> list){
        Map<String, Object> resultMap = ConUtils.hashmap();
        resultMap.put("code", "0");
        if(ConUtils.isNotNull(list)){
            resultMap.put("code", "1");
            resultMap.put("datas", list);
        }
        return this.toJsonString(resultMap);
    }
}
