package com.enjoylife.blogs;

import com.enjoylife.article.vo.ArticleEntity;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.view.Page;

import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
public interface IBlogsESService {

    List<ArticleEntity> selectArticlesByPage(Page<NewArticle> page);

    void insertArticlesByList(List<NewArticle> list);
}
