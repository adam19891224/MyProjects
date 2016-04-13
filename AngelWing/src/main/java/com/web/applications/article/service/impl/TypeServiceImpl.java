package com.web.applications.article.service.impl;

import com.system.webui.Page;
import com.system.webui.Tree;
import com.tools.utils.ConUtils;
import com.tools.utils.StringUtils;
import com.web.applications.article.dao.TypeMapper;
import com.web.applications.article.entity.Type;
import com.web.applications.article.form.ArticleForm;
import com.web.applications.article.service.TypeService;
import com.web.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/19
 */
@Service
public class TypeServiceImpl extends BaseServiceImpl<Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> selectByPage(Page<Type> page) {
        return typeMapper.selectByPage(page);
    }

    @Override
    public Integer getCounts(Page<Type> page) {
        return typeMapper.getCounts(page);
    }

    @Override
    public void insertType(Type type) {
        type.setTypeId(UUID.randomUUID().toString());
        typeMapper.insertSelective(type);
    }

    @Override
    public void updateByPrimaryKeySelective(Type record) {
        typeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void deleteByPrimarkId(String id) {
        typeMapper.deleteByPrimaryId(id);
    }

    @Override
    public List<Tree> selectTree(ArticleForm form) {

        Map<String, Object> map = ConUtils.hashmap();

        if(StringUtils.isNotNull(form.getNoParent()))
            map.put("noParent", form.getNoParent());
        if(StringUtils.isNotNull(form.getParentId()))
            map.put("parentId", form.getParentId());

        return typeMapper.selectTree(map);
    }

    @Override
    public List<Tree> selectToTree() {
        return typeMapper.selectToTree();
    }
}
