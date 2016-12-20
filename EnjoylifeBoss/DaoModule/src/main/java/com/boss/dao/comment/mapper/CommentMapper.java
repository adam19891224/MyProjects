package com.boss.dao.comment.mapper;

import com.boss.dao.comment.pojo.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentSid);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentSid);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    void deleteCommentsByArticleId(String id);
}