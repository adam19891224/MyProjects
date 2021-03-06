package com.enjoylife.article.impl;

import com.enjoylife.article.IArticlesESService;
import com.enjoylife.article.repository.IArticleESRepository;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.modules.ArticleEntity;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.EsResultCastUtils;
import com.enjoylife.view.Page;
import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
@Service
public class ArticlesESServiceImpl extends BaseAbstractClass implements IArticlesESService {


    @Resource
    private IArticleESRepository blogESRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;
    @Resource(name = "articlesESResultMapperImpl")
    private SearchResultMapper searchResultMapper;

    @Override
    public Map<String, Object> selectArticlesHighlightByPage(Page<NewArticle> page) {

        String kw = page.getKw();
        BoolQueryBuilder bool = new BoolQueryBuilder();

        //设置title的查询规则
//        bool.should(QueryBuilders.matchQuery("articleTitle", kw)
//                .boost(2.0f));
//        bool.should(QueryBuilders.matchQuery("articleDescription", kw));

        //设置多字段查询规则
        bool.should(QueryBuilders.multiMatchQuery(kw, "articleTitle", "articleDescription")
                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                .tieBreaker(0.3f)
                .minimumShouldMatch("30%"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bool)
                .withHighlightFields(new HighlightBuilder.Field("articleTitle").preTags("<u class=\"search-red\">").postTags("</u>"),
                        new HighlightBuilder.Field("articleDescription").preTags("<span class=\"search-red\">").postTags("</span>"))
                .withPageable(new PageRequest(page.getEsPage(), page.getPageSize()))
                .build();

        org.springframework.data.domain.Page<ArticleEntity> esResult = elasticsearchTemplate.queryForPage(searchQuery, ArticleEntity.class, searchResultMapper);

        Map<String, Object> map = ConUtils.hashmap();
        map.put("totalCount", esResult.getTotalElements());
        map.put("totalPage", esResult.getTotalPages());
        map.put("result", Lists.newArrayList(esResult.iterator()));

        return map;
    }

    @Override
    public List<ArticleEntity> selectAllArticles() {
        BoolQueryBuilder bool = new BoolQueryBuilder();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(bool).build();
        return elasticsearchTemplate.queryForList(searchQuery, ArticleEntity.class);
    }

    @Override
    public void insertArticlesByList(List<NewArticle> list) {
        List<ArticleEntity> list1 = new ArrayList<>();
        list.forEach(e -> {
            ArticleEntity entity = new ArticleEntity();
            BeanUtils.copyProperties(e, entity);
            list1.add(entity);
        });

        blogESRepository.save(list1);
    }

}
