package com.web.base.service.impl;

import com.system.webui.Page;
import com.web.base.service.BaseService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Administrator
 * Date: 2015/11/25
 */
public abstract class BaseServiceImpl <E> implements BaseService<E> {

    //创建service指定的日志输入器
    protected Logger logger = Logger.getLogger("ServiceLogger");

    @Override
    public List<E> selectByPage(Page<E> page) {
        return null;
    }

    @Override
    public Integer getCounts(Page<E> page) {
        return 0;
    }

}
