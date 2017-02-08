package com.enjoylife.blogs;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.view.Page;

import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
public interface IBlogsESService {

    Map<String, Object> selectArticlesHighlightByPage(Page<NewArticle> page);

    void insertArticlesByList(List<NewArticle> list);
}
