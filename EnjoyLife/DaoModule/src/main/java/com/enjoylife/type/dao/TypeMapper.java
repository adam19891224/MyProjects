package com.enjoylife.type.dao;

import com.enjoylife.type.vo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer typeSid);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer typeSid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> selectAllTypes();

    Type selectTypeByArticleId(@Param("articleID") String articleID);

    int selectAllTypesCount();
}