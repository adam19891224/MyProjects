package com.enjoylife.blogs.impl;

import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.blogs.IBlogsESService;
import com.enjoylife.blogs.repository.IBlogESRepository;
import com.enjoylife.models.ArticleEntity;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.EsResultCastUtils;
import com.enjoylife.view.Page;
import com.google.common.collect.Lists;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
@Service
public class BlogsESServiceImpl extends BaseAbstractClass implements IBlogsESService {

    @Autowired
    private IBlogESRepository blogESRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<ArticleEntity> selectArticlesByPage(Page<NewArticle> page) {
        org.springframework.data.domain.Page<ArticleEntity> resP = this.blogESRepository.findArticlesByArticleTitle(page.getKw(), new PageRequest(page.getEsPage(), page.getPageSize()));
        Iterator<ArticleEntity> iterator = resP.iterator();
        return Lists.newArrayList(iterator);
    }

    @Override
    public Map<String, Object> selectArticlesHighlightByPage(Page<NewArticle> page) {

        String kw = page.getKw();
        BoolQueryBuilder bool = new BoolQueryBuilder();
        String[] kws = kw.split(" ");
        for(String temp : kws){
            bool.should(QueryBuilders.matchQuery("articleTitle", temp).boost(2.0f));
            bool.should(QueryBuilders.matchQuery("articleDescription", temp));
            bool.should(QueryBuilders.wildcardQuery("articleTitle", "*" + temp + "*"));
            bool.should(QueryBuilders.wildcardQuery("articleDescription", "*" + temp + "*"));
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(bool)
                .withHighlightFields(
                        new HighlightBuilder.Field("articleTitle").preTags("<u class=\"search-red\">").postTags("</u>"),
                        new HighlightBuilder.Field("articleDescription").preTags("<span class=\"search-red\">").postTags("</span>"))
                .withPageable(new PageRequest(page.getEsPage(), page.getPageSize()))
                .build();


        org.springframework.data.domain.Page<ArticleEntity> resP = elasticsearchTemplate.queryForPage(searchQuery, ArticleEntity.class, new SearchResultMapper() {
            @Override
            public <T> org.springframework.data.domain.Page<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<ArticleEntity> list = ConUtils.arraylist();
                SearchHits searchHits = response.getHits();
                long total = searchHits.getTotalHits();
                SearchHit[] hits = searchHits.hits();
                if (hits.length > 0) {
                    searchHits.forEach(
                            obj
                                    ->
                            {
                                Map<String, Object> result = obj.getSource();
                                Map<String, HighlightField> resultH = obj.getHighlightFields();
                                ArticleEntity articleEntity = null;
                                try {
                                    articleEntity = EsResultCastUtils.getEntityByMap(result);
                                } catch (InvocationTargetException | IllegalAccessException e) {
                                    logger.error("es搜索 map结果转bean错误：", e);
                                }
                                if(articleEntity != null){
                                    if(resultH.containsKey("articleTitle"))
                                        articleEntity.setArticleTitle(resultH.get("articleTitle").fragments()[0].toString());
                                    if(resultH.containsKey("articleDescription"))
                                        articleEntity.setArticleDescription(resultH.get("articleDescription").fragments()[0].toString());
                                    list.add(articleEntity);
                                }
                            }
                    );
                    if (list.size() > 0) {
                        return new PageImpl<T>((List<T>) list, pageable, total);
                    }
                }
                return new PageImpl<T>((List<T>) list, pageable, 0);
            }
        });

        Map<String, Object> map = ConUtils.hashmap();
        map.put("totalCount", resP.getTotalElements());
        map.put("totalPage", resP.getTotalPages());
        map.put("result", Lists.newArrayList(resP.iterator()));

        return map;
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
