package com.enjoylife.comment.dao;

import com.enjoylife.comment.vo.ArticleComment;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Integer articleCommentId);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Integer articleCommentId);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);
}