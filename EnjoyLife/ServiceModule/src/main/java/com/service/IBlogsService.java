package com.service;

import com.article.vo.ArticleWithBLOBs;
import com.foundation.form.ArticleForm;
import com.foundation.view.Page;

/**
 * Created by adam on 2016/4/23.
 */
public interface IBlogsService {

    Page<ArticleWithBLOBs> selectArticlesByPage(Page<ArticleWithBLOBs> page);

    ArticleWithBLOBs selectArticleBySID(Integer sid);

    void saveArticle(ArticleForm form);

}
