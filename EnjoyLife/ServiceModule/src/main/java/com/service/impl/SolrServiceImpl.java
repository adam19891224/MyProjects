package com.service.impl;

import com.article.show.NewArticle;
import com.foundation.utils.ConUtils;
import com.foundation.view.Page;
import com.service.ISolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/5/5
 */
@Service
public class SolrServiceImpl extends BaseServiceImpl implements ISolrService {



    @Override
    public Page<NewArticle> selectArticlesByPage(Page<NewArticle> page) {
        logger.info("进入通过solr引擎查询文章列表方法");
        QueryResponse response = null;
        SolrQuery query = new SolrQuery();
        query.setHighlight(true);                //开启高亮
        query.setHighlightSimplePre("<font color = \"red\">");    //前缀
        query.setHighlightSimplePost("</font>");    //后缀
        query.setParam("hl.fl", "articleTitle");    //高亮的字段
        query.set("q", "articleTitle:(测试)");
        query.addFilterQuery("articleDescription:(完整)");
        query.addSort(new SolrQuery.SortClause("createDate", SolrQuery.ORDER.desc));
        query.set("start", page.getPageNum());
        query.set("rows", page.getPageSize());
        try {
            response = client.query(query);
            Map<String, Map<String, List<String>>>  map = response.getHighlighting();
            SolrDocumentList results = response.getResults();
            List<NewArticle> list = ConUtils.arraylist();
            NewArticle newArticle;
            if(results.size() > 0){
                page.setPage(page.getPage() + 1);
            }
            for(SolrDocument document : results){
                newArticle = new NewArticle();
                newArticle.setArticleSid((Integer) document.get("articleSid"));
                newArticle.setArticleId((String) document.get("articleId"));
                newArticle.setArticleImg((String) document.get("articleImg"));
                newArticle.setArticleTitle((String) document.get("articleTitle"));
                newArticle.setHighLightTitle(map.get(document.get("articleSid").toString()).get("articleTitle").get(0));
                newArticle.setArticleDescription((String) document.get("articleDescription"));
                newArticle.setCreateDate((Date) document.get("createDate"));
                newArticle.setUpdateDate((Date) document.get("updateDate"));
                list.add(newArticle);
            }
            page.setResultList(list);
        } catch (SolrServerException | IOException e) {
            logger.error("通过solr引擎查询文章列表方法发生异常");
            logger.error(e);
        }
        return page;
    }
}
