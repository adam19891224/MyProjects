package com.type.dao;

import com.type.vo.ArticleType;

public interface ArticleTypeMapper {
    int deleteByPrimaryKey(String articleTypeId);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(String articleTypeId);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}