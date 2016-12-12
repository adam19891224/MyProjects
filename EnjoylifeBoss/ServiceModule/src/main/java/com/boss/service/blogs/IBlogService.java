package com.boss.service.blogs;

import com.boss.dao.blog.pojo.ArticleBossPJ;
import com.boss.foundation.entity.ArticleEntity;
import com.boss.foundation.entity.EnjoyFile;
import com.boss.foundation.view.Page;

/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
public interface IBlogService {

    String toUploadFile(EnjoyFile file);

    String saveBlog(ArticleEntity entity);

    Page<ArticleBossPJ> selectArticleByPage(Page<ArticleBossPJ> page);

    boolean refresh();
}
