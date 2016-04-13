package com.service.impl;

import com.article.domain.ArticleDomain;
import com.article.domain.CommentDomain;
import com.article.domain.TypeDomain;
import com.article.vo.ArticleWithBLOBs;
import com.article.vo.Comment;
import com.foundation.form.ArticleForm;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import com.foundation.view.Tree;
import com.service.IEnjoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
@Service
public class EnjoyServiceImpl implements IEnjoyService {

    @Autowired
    private ArticleDomain articleDomain;
    @Autowired
    private TypeDomain typeDomain;
    @Autowired
    private CommentDomain commentDomain;


    @Override
    public Page<ArticleWithBLOBs> selectArticlesByPage(Page<ArticleWithBLOBs> page) {
        return articleDomain.selectByPage(page);
    }

    @Override
    public ArticleWithBLOBs selectArticleBySID(Integer sid) {
        return articleDomain.selectBySID(sid);
    }

    @Override
    public List<Tree> selectTypeToTree() {
        return typeDomain.selectToTree();
    }

    @Override
    public void saveArticle(ArticleForm form) {
        articleDomain.save(form);
    }

    @Override
    public Page<Comment> selectCommentByPage(Page<Comment> page) {
        return commentDomain.selectByPage(page);
    }

    @Override
    public Integer selectCommentCountsByArticle(String articleId) {
        return commentDomain.selectCountsByArticle(articleId);
    }

    @Override
    public void saveComment(CommentForm form) {
        commentDomain.saveComment(form);
    }
}
