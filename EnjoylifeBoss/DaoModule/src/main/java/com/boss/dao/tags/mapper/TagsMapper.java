package com.boss.dao.tags.mapper;

import com.boss.dao.tags.pojo.Tags;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagSid);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagSid);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);

    void deleteTagsByArticleId(String id);
}