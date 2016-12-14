package com.boss.dao.blog.repository;

import com.boss.foundation.entity.ArticleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
public interface IBlogESRepository extends ElasticsearchCrudRepository<ArticleEntity, Long> {

}
