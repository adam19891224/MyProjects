package com.enjoylife.article;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.modules.ArticleEntity;
import com.enjoylife.view.Page;

import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
public interface IArticlesESService {

    Map<String, Object> selectArticlesHighlightByPage(Page<NewArticle> page);

    List<ArticleEntity> selectAllArticles();

    void insertArticlesByList(List<NewArticle> list);
}
