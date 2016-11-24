package com.enjoylife.blogs.repository;

import com.enjoylife.article.vo.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
public interface IBlogESRepository extends PagingAndSortingRepository<ArticleEntity, Long> {

    @Query("{\"bool\" : {\"should\" : [{\"match\" : {\"articleTitle\" : \"?0\"}}, {\"match\" :{\"articleDescription\" : \"?0\"}}, {\"wildcard\" : {\"articleTitle\" : \"*?0*\"}}, {\"wildcard\" :{\"articleDescription\" : \"*?0*\"}}]}}")
//    @Query("{\"bool\" : {\"should\" : [{\"wildcard\" : {\"articleTitle\" : \"*?0*\"}}, {\"wildcard\" :{\"articleDescription\" : \"*?0*\"}}]}}")
    Page<ArticleEntity> findArticlesByArticleTitle(String keyword, Pageable pageable);

}
