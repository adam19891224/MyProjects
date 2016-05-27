package com.article.search;

import com.article.vo.NewArticle;
import com.foundation.view.Page;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/8
 */
public interface SolrArticleMapper {

    Map<String, Object> selectArticlesByPage(Page<NewArticle> page) throws IOException, SolrServerException;

}
