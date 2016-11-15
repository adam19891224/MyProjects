package com.enjoylife.type.dao;

import com.enjoylife.type.vo.ArticleType;

public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Integer articleTypeId);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Integer articleTypeId);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}