package com.article.dao;

import com.article.vo.Type;
import com.foundation.view.Tree;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer typeSid);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer typeSid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Tree> selectToTree();
}