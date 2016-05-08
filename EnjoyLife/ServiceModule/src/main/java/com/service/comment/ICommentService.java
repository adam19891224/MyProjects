package com.service.comment;

import com.comment.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
public interface ICommentService {

    Page<Comment> selectCommentByPage(Page<Comment> page);

    Integer selectCommentCountsByArticle(String articleId);

    void saveComment(CommentForm form);

}
