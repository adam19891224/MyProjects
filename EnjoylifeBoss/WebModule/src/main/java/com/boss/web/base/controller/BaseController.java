package com.boss.web.base.controller;

import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.utils.SessionKeyUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * ranmin-zhouyuhong
 * 2016/11/30
 */
public abstract class BaseController {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected static final String[] IMG_TYPE = {".jpg", ".png", ".gif", ".bmp"};

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

}
