package com.boss.dao.tags.mapper;

import com.boss.dao.tags.pojo.ArticleTags;

public interface ArticleTagsMapper {
    int deleteByPrimaryKey(Integer tagArticleId);

    int insert(ArticleTags record);

    int insertSelective(ArticleTags record);

    ArticleTags selectByPrimaryKey(Integer tagArticleId);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);
}