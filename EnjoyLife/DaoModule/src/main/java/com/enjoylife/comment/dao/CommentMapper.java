package com.enjoylife.comment.dao;

import com.enjoylife.comment.vo.Comment;
import com.enjoylife.form.CommentForm;
import com.enjoylife.view.Page;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentSid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentSid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectByPage(Page<Comment> page);

    Integer selectCountsByArticle(Page<Comment> page);

    List<Comment> selectReplyComment(CommentForm form);
}