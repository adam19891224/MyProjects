package com.enjoylife.tags.impl;

import com.enjoylife.tags.ITagesService;
import com.enjoylife.tags.dao.TagsMapper;
import com.enjoylife.tags.vo.Tags;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/11/15
 */
@Service
public class TagsServiceImpl implements ITagesService{

    @Resource
    private TagsMapper tagsMapper;

    @Override
    public List<Tags> selectTagsByArticleId(String articleID) {
        return tagsMapper.selectTagsByArticleId(articleID);
    }
}
