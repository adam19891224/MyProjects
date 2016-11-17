package com.enjoylife.comment;

import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.view.Page;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
public interface ICommentService {

    Page<Comment> getCommentsByPage(Page<Comment> page);

    String insertCommentByForm(CommentForm form);
}
