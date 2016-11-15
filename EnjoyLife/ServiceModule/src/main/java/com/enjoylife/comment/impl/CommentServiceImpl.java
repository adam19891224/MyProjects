package com.enjoylife.comment.impl;

import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.comment.ICommentService;
import com.enjoylife.comment.dao.ArticleCommentMapper;
import com.enjoylife.comment.dao.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
@Service
public class CommentServiceImpl extends BaseAbstractClass implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleCommentMapper articleCommentMapper;

}
