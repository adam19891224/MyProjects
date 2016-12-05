package com.boss.service.blogs;

import com.boss.foundation.entity.ArticleEntity;
import com.boss.foundation.entity.EnjoyFile;

/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
public interface IBlogService {

    String toUploadFile(EnjoyFile file);

    String saveBlog(ArticleEntity entity);
}
