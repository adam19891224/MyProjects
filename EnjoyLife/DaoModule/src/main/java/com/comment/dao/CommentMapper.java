package com.comment.dao;

import com.comment.vo.Comment;
import com.foundation.form.CommentForm;
import com.foundation.view.Page;
import org.apache.ibatis.annotations.Param;

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

    Integer selectCountsByArticle(@Param(value = "articleId") String articleId);

    List<Comment> selectReplyComment(CommentForm form);
}