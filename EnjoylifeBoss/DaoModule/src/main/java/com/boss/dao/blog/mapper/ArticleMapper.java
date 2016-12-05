package com.boss.dao.blog.mapper;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.blog.pojo.ArticleWithBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleSid);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleSid);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}