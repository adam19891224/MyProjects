package com.enjoylife.article.dao;

import com.enjoylife.article.vo.Article;
import com.enjoylife.article.vo.ArticleTime;
import com.enjoylife.article.vo.ArticleWithBLOBs;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.view.Page;
import org.apache.ibatis.annotations.Param;

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

    List<NewArticle> selectTypeArticlesByPage(Page<NewArticle> page);

    int selectTypeArticlesCountsByPage(Page<NewArticle> page);

    List<ArticleTime> selectTimeGroupByArticle();

    String selectArticleInfoByArticleID(@Param("articleID") String articleID);
}