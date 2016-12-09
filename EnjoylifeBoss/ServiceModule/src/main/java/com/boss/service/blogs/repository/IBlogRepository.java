package com.boss.service.blogs.repository;

import com.boss.foundation.modules.ArticleESEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
public interface IBlogRepository extends CrudRepository<ArticleESEntity, Long> {
}
