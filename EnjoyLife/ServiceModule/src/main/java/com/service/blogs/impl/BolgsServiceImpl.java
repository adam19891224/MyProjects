package com.service.blogs.impl;

import com.article.dao.ArticleMapper;
import com.article.search.SolrArticleMapper;
import com.article.vo.ArticleWithBLOBs;
import com.article.vo.NewArticle;
import com.foundation.enums.SolrResultMapKeyEnum;
import com.foundation.utils.ConUtils;
import com.foundation.view.Page;
import com.service.base.BaseAbstractClass;
import com.service.blogs.IBlogsService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/23
 */
@Service
public class BolgsServiceImpl extends BaseAbstractClass implements IBlogsService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SolrArticleMapper solrArticleMapper;

    @Override
    public Page<NewArticle> selectArticlesByPage(Page<NewArticle> page) {
        {
            List<NewArticle> list = articleMapper.selectByPage(page);
            if(ConUtils.isNotNull(list)){
                page.setResultList(list);
                if(page.getTotalCounts() == null){
                    int count = articleMapper.selectCountsByPage(page);
                    page.setTotalCounts(count);
                    //总页数计算方法：总记录数 + 每页显示记录数 - 1 的结果 / 每页显示记录数
                    page.setTotalPages((count + page.getPageSize() - 1) / page.getPageSize());
                }
            }else{
                page.setResultList(ConUtils.arraylist());
            }
        }
        return page;
    }

    @Override
    public Page<NewArticle> selectArticlesByPageSolr(Page<NewArticle> page) {
        try {
            {
                Map<String, Object> result = solrArticleMapper.selectArticlesByPage(page);
                List<NewArticle> list = (List<NewArticle>) result.get(SolrResultMapKeyEnum.ResultSet.getKey());
                page.setResultList(list);
                if(page.getTotalCounts() == null){
                    int count = (int) result.get(SolrResultMapKeyEnum.ResultCounts.getKey());
                    page.setTotalCounts(count);
                    //总页数计算方法：总记录数 + 每页显示记录数 - 1 的结果 / 每页显示记录数
                    page.setTotalPages((count + page.getPageSize() - 1) / page.getPageSize());
                }
            }
        } catch (IOException | SolrServerException e) {
            logger.error("solr查询结果发生异常：==============================》");
            logger.error("异常原因：  " + e);
        }
        return page;
    }

    @Override
    public ArticleWithBLOBs selectArticleBySID(Integer sid) {
        return articleMapper.selectByPrimaryKey(sid);
    }

    @Override
    public List<NewArticle> selectHotsForEight() {
        return articleMapper.selectHotsLimitEight();
    }

}
