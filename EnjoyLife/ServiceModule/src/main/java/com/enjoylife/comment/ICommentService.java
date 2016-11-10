package com.enjoylife.comment;

import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.view.Page;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
public interface ICommentService {

    Page<Comment> selectCommentByPage(Page<Comment> page);

    List<Comment> selectReplyCommentByForm(CommentForm form);

    void saveComment(CommentForm form);

}
