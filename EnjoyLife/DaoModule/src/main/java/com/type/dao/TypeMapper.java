package com.type.dao;

import com.type.vo.Type;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer typeSid);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer typeSid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> selectAllTypes();
}