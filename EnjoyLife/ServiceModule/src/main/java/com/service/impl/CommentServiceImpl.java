package com.service.impl;

import com.article.domain.CommentDomain;
import com.article.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import com.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl implements ICommentService {

    @Autowired
    private CommentDomain commentDomain;

    @Override
    public Page<Comment> selectCommentByPage(Page<Comment> page) {
        logger.info("进入查询评论分页方法");
        return commentDomain.selectByPage(page);
    }

    @Override
    public Integer selectCommentCountsByArticle(String articleId) {
        return commentDomain.selectCountsByArticle(articleId);
    }

    @Override
    public void saveComment(CommentForm form) {
        logger.info("进入保存评论方法");
        commentDomain.saveComment(form);
    }
}
