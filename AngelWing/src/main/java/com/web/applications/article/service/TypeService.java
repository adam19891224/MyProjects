package com.web.applications.article.service;

import com.system.webui.Page;
import com.system.webui.Tree;
import com.web.applications.article.entity.Type;
import com.web.applications.article.form.ArticleForm;
import com.web.base.service.BaseService;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/19
 */
public interface TypeService extends BaseService<Type> {

    List<Type> selectByPage(Page<Type> page);

    Integer getCounts(Page<Type> page);

    void insertType(Type type);

    void updateByPrimaryKeySelective(Type record);

    void deleteByPrimarkId(String id);

    List<Tree> selectTree(ArticleForm form);

    List<Tree> selectToTree();
}
