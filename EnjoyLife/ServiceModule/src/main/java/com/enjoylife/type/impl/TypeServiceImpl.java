package com.enjoylife.type.impl;

import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.type.ITypeService;
import com.enjoylife.type.dao.TypeMapper;
import com.enjoylife.type.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/2
 */
@Service
public class TypeServiceImpl extends BaseAbstractClass implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> selectAllTypes() {
        return typeMapper.selectAllTypes();
    }

    @Override
    public Type selectTypeByArticleId(String articleID) {
        return typeMapper.selectTypeByArticleId(articleID);
    }

    @Override
    public int selectAllTypesCount() {
        return typeMapper.selectAllTypesCount();
    }
}
