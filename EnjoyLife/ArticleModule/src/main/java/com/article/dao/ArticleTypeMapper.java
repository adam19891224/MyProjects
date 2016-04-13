package com.article.dao;

import com.article.vo.ArticleType;

public interface ArticleTypeMapper {
    int deleteByPrimaryKey(String articleTypeId);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(String articleTypeId);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}