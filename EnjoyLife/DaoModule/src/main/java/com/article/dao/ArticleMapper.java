package com.article.dao;

import com.article.vo.Article;
import com.article.vo.ArticleWithBLOBs;
import com.article.vo.NewArticle;
import com.foundation.view.Page;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleSid);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleSid);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    List<NewArticle> selectByPage(Page<NewArticle> page);

    int selectCountsByPage(Page<NewArticle> page);
}