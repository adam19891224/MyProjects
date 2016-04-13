package com.article.domain;

import com.article.dao.ArticleCommentMapper;
import com.article.vo.ArticleComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/26
 */
@Component
public class ArticleCommentDomain {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    public void save(String articleId, String commentId){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleId(articleId);
        articleComment.setCommentId(commentId);
        articleComment.setArticleCommentId(UUID.randomUUID().toString());
        articleCommentMapper.insertSelective(articleComment);
    }

}
