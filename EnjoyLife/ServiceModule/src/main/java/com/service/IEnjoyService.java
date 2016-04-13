package com.service;

import com.article.vo.ArticleWithBLOBs;
import com.article.vo.Comment;
import com.foundation.form.ArticleForm;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import com.foundation.view.Tree;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
public interface IEnjoyService {

    Page<ArticleWithBLOBs> selectArticlesByPage(Page<ArticleWithBLOBs> page);

    ArticleWithBLOBs selectArticleBySID(Integer sid);

    List<Tree> selectTypeToTree();

    void saveArticle(ArticleForm form);

    Page<Comment> selectCommentByPage(Page<Comment> page);

    Integer selectCommentCountsByArticle(String articleId);

    void saveComment(CommentForm form);

}
