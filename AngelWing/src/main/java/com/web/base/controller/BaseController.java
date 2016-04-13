package com.web.base.controller;

import com.system.webui.Page;
import com.tools.utils.ConUtils;
import com.web.base.service.BaseService;
import com.web.manager.user.entity.User;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/10
 */
@Controller
public class BaseController {

    //创建controller指定的日志生成器
    protected Logger logger = Logger.getLogger("ControllerLogger");

    /**
     * 转换page为map对象，返回给前台
     * @param page
     * @return
     */
    protected <T> Map<String, Object> formatMap(Page<T> page){
        Map<String, Object> map = ConUtils.hashmap();
        map.put("total", page.getTotal());
        map.put("rows", page.getResultList());
        return map;
    }

    /**
     * 得到page<E> 结果
     */
    protected <T> Page<T> getPage(Page<T> page, BaseService<T> service){
        page.setResultList(service.selectByPage(page));
        page.setTotal(service.getCounts(page));
        return page;
    }


    /**
     * 得到session
     */
    protected Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 得到当前用户
     * @return
     */
    public User getCurrentUser() {
        return (User) this.getSession().getAttribute("user");
    }
}
