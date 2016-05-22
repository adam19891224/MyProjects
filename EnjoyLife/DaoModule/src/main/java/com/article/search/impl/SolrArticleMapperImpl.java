package com.article.search.impl;

import com.article.search.SolrArticleMapper;
import com.article.vo.NewArticle;
import com.foundation.solr.factory.SolrFactory;
import com.foundation.utils.ConUtils;
import com.foundation.utils.StringUtils;
import com.foundation.view.Page;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by adam on 2016/5/8.
 */
@Repository
public class SolrArticleMapperImpl implements SolrArticleMapper {

    private SolrClient solrClient;

    public SolrArticleMapperImpl(){
        this.solrClient = SolrFactory.getClient();
    }


    @Override
    public Page<NewArticle> selectArticlesByPage(Page<NewArticle> page) {
        boolean isHighlight = false;
        QueryResponse response = null;

        //获取solrquery查询对象
        SolrQuery query = this.getSolrQueryByPage(page);

        //如果是搜索查询，则把highlight设置为true
        if(StringUtils.isNotNull(page.getKw())){
            isHighlight = true;
        }
        try {
            response = solrClient.query(query);
            Map<String, Map<String, List<String>>>  map = response.getHighlighting();
            SolrDocumentList results = response.getResults();
            List<NewArticle> list = ConUtils.arraylist();
            NewArticle newArticle;
            if(results.size() > 0){
//                page.setPage(page.getPage() + 1);
                for(SolrDocument document : results){
                    newArticle = new NewArticle();
                    newArticle.setArticleSid((Integer) document.get("articleSid"));
                    newArticle.setArticleId((String) document.get("articleId"));
                    newArticle.setArticleImg((String) document.get("articleImg"));
                    newArticle.setArticleTitle((String) document.get("articleTitle"));
                    if(isHighlight){
                        newArticle.setHighLightTitle(map.get(document.get("articleSid").toString()).get("articleTitle").get(0));
                    }
                    newArticle.setArticleDescription((String) document.get("articleDescription"));
                    newArticle.setCreateDate((Date) document.get("createDate"));
                    newArticle.setUpdateDate((Date) document.get("updateDate"));
                    list.add(newArticle);
                }
                page.setResultList(list);
                if(page.getTotalCounts() == null){
                    int count = (int) results.getNumFound();
                    page.setTotalCounts(count);
                    //总页数计算方法：总记录数 + 每页显示记录数 - 1 的结果 / 每页显示记录数
                    page.setTotalPages((count + page.getPageSize() - 1) / page.getPageSize());
                }
            }
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    //组装solr的query查询对象
    private <T> SolrQuery getSolrQueryByPage(Page<T> page){

        SolrQuery query = new SolrQuery();
        query.addSort(new SolrQuery.SortClause("createDate", SolrQuery.ORDER.desc));
        query.set("start", page.getPageNum());
        query.set("rows", page.getPageSize());
        //如果是搜索查询，则创建高亮，搜索范围
        if(StringUtils.isNotNull(page.getKw())){
            query.set("q", "articleTitle:(" + page.getKw() + ")");
            query.addFilterQuery("articleDescription:(" + page.getKw() + ")");
            query.setHighlight(true);                //开启高亮
            query.setHighlightSimplePre("<font color = \"red\">");    //前缀
            query.setHighlightSimplePost("</font>");    //后缀
            query.setParam("hl.fl", "articleTitle");    //高亮的字段
        }else{
            query.set("q", "*:*");
        }
        return query;
    }
}
