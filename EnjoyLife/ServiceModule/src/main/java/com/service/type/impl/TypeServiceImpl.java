package com.service.type.impl;

import com.service.base.BaseServiceImpl;
import com.service.type.ITypeService;
import com.type.dao.TypeMapper;
import com.type.vo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/2
 */
@Service
public class TypeServiceImpl extends BaseServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> selectAllTypes() {
        return typeMapper.selectAllTypes();
    }
}
