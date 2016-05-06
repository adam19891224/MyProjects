package com.service;

import com.article.show.NewArticle;
import com.foundation.view.Page;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/5
 */
public interface ISolrService {

    /**
     * 根据Page对象获取分页信息
     * @param page
     * @return
     */
    Page<NewArticle> selectArticlesByPage(Page<NewArticle> page);

}
