package com.boss.dao.blog.mapper;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.blog.pojo.ArticleBossPJ;
import com.boss.dao.blog.pojo.ArticleWithBLOBs;
import com.boss.foundation.modules.ArticleESEntity;
import com.boss.foundation.view.Page;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleSid);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleSid);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    ArticleESEntity selectByArticleId(String id);

    List<ArticleESEntity> selectAllForRefresh();

    List<ArticleBossPJ> selectArticleByPage(Page<ArticleBossPJ> page);

    int selectArticleCountsByPage(Page<ArticleBossPJ> page);

    List<Article> selectArticlesByTypeID(String id);

    List<Article> selectArticlesWithOutTypeID(String id);

    List<Article> selectArticlesBySeriesId(String id);

    List<Article> selectArticlesWithOutSeriesID(String id);
}