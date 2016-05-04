package com.service;

import com.article.vo.Comment;
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

    List<Tree> selectTypeToTree();

    Page<Comment> selectCommentByPage(Page<Comment> page);

    Integer selectCommentCountsByArticle(String articleId);

    void saveComment(CommentForm form);

}
