package com.enjoylife.article.impl;

import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.modules.ArticleEntity;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.utils.EsResultCastUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2017/4/11
 */
@Service("articlesESResultMapperImpl")
public class ArticlesESResultMapperImpl extends BaseAbstractClass implements SearchResultMapper {
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
        List<ArticleEntity> list = ConUtils.arraylist();
        SearchHits searchHits = searchResponse.getHits();
        long total = searchHits.getTotalHits();
        {
            try {
                SearchHit[] hits = searchHits.hits();
                Map<String, Object> result;
                Map<String, HighlightField> resultH;
                ArticleEntity articleEntity;
                for (SearchHit temp : hits) {
                    result = temp.getSource();
                    resultH = temp.getHighlightFields();

                    articleEntity = EsResultCastUtils.getEntityByMap(result);

                    if (articleEntity != null) {
                        if (resultH.containsKey("articleTitle"))
                            articleEntity.setArticleTitle(resultH.get("articleTitle").fragments()[0].toString());

                        if (resultH.containsKey("articleDescription"))
                            articleEntity.setArticleDescription(resultH.get("articleDescription").fragments()[0].toString());

                        list.add(articleEntity);
                    }
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                logger.error("es搜索 map结果转bean错误：", e);
                return new AggregatedPageImpl<T>(new ArrayList<>(), pageable, 0);
            }
        }
        return new AggregatedPageImpl<T>((List<T>) list, pageable, total);
    }
}
