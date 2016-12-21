package com.boss.service.tags;

import com.boss.dao.tags.pojo.Tags;
import com.boss.dao.tags.pojo.TagsInfo;
import com.boss.foundation.view.Page;

/**
 * ranmin-zhouyuhong
 * 2016/12/21
 */
public interface ITagsService {

    Page<TagsInfo> selectTagsByPage(Page<TagsInfo> page);

    String saveTagInfo(Tags tags);

    String deleteTagSafeByTagSid(Integer id);
}
