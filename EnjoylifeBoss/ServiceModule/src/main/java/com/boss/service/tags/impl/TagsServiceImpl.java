package com.boss.service.tags.impl;

import com.boss.dao.tags.mapper.TagsMapper;
import com.boss.dao.tags.pojo.Tags;
import com.boss.dao.tags.pojo.TagsInfo;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.view.Page;
import com.boss.service.base.AbstractService;
import com.boss.service.tags.ITagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * ranmin-zhouyuhong
 * 2016/12/21
 */
@Service
public class TagsServiceImpl extends AbstractService implements ITagsService {

    @Resource
    private TagsMapper tagsMapper;

    @Override
    public Page<TagsInfo> selectTagsByPage(Page<TagsInfo> page) {
        List<TagsInfo> result = tagsMapper.selectTagsByPage(page);
        if(ConUtils.isNotNull(result)){
            int count = tagsMapper.selectTagsCountByPage(page);
            page.setTotalCounts(count);
            page.setResultList(result);
            page.setSuccess(true);
        }
        return page;
    }

    @Override
    public String saveTagInfo(Tags tags) {
        tags.setTagId(UUID.randomUUID().toString());
        tagsMapper.insertSelective(tags);
        return "success";
    }

    @Override
    @Transactional
    public String deleteTagSafeByTagSid(Integer id) {
        try {
            tagsMapper.deleteTagsBySid(id);
            tagsMapper.deleteByPrimaryKey(id);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }

        return "error";
    }

    @Override
    public List<Tags> selectArticleTagsByArticleId(String id) {
        return tagsMapper.selectArticleTagsByArticleId(id);
    }

    @Override
    public List<Tags> selectUnTagsByArticleId(String id) {
        return tagsMapper.selectUnTagsByArticleId(id);
    }
}
