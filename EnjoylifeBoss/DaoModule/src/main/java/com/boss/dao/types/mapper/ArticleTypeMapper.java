package com.boss.dao.types.mapper;

import com.boss.dao.types.pojo.ArticleType;

public interface ArticleTypeMapper {
    int deleteByPrimaryKey(Integer articleTypeId);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Integer articleTypeId);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);

}