package com.boss.dao.tags.mapper;

import com.boss.dao.tags.pojo.ArticleTags;
import com.boss.dao.tags.pojo.Tags;
import com.boss.dao.tags.pojo.TagsInfo;
import com.boss.foundation.view.Page;

import java.util.List;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagSid);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagSid);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);

    void deleteTagsByArticleId(String id);

    List<TagsInfo> selectTagsByPage(Page<TagsInfo> page);

    int selectTagsCountByPage(Page<TagsInfo> page);

    void deleteTagsBySid(Integer id);

    List<Tags> selectArticleTagsByArticleId(String id);

    List<Tags> selectUnTagsByArticleId(String id);

    void insertBatchTags(List<ArticleTags> inserts);
}