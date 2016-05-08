package com.article.search;

import com.article.vo.NewArticle;
import com.foundation.view.Page;

/**
 * Created by adam on 2016/5/8.
 */
public interface SolrArticleMapper {

    Page<NewArticle> selectArticlesByPage(Page<NewArticle> page);

}
