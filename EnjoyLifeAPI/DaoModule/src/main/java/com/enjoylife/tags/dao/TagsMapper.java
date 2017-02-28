package com.enjoylife.tags.dao;

import com.enjoylife.tags.vo.Tags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagSid);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagSid);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);

    List<Tags> selectTagsByArticleId(@Param("articleID") String articleID);
}