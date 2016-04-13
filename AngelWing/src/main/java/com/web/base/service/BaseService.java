package com.web.base.service;

import com.system.webui.Page;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/12
 */
public interface BaseService<E> {

    List<E> selectByPage(Page<E> page);

    Integer getCounts(Page<E> page);

}
