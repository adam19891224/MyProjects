package com.enjoylife.tags;

import com.enjoylife.tags.vo.Tags;

import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/11/15
 */
public interface ITagesService {

    List<Tags> selectTagsByArticleId(String articleID);

}
