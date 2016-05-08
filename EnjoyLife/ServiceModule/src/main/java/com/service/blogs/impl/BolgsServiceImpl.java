package com.service.blogs.impl;

import com.article.dao.ArticleMapper;
import com.article.search.SolrArticleMapper;
import com.article.vo.ArticleWithBLOBs;
import com.article.vo.NewArticle;
import com.foundation.view.Page;
import com.service.base.BaseServiceImpl;
import com.service.blogs.IBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/23
 */
@Service
public class BolgsServiceImpl extends BaseServiceImpl implements IBlogsService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SolrArticleMapper solrArticleMapper;

    @Override
    public Page<NewArticle> selectArticlesByPage(Page<NewArticle> page) {
        logger.info("进入查询文章分页方法");
        return solrArticleMapper.selectArticlesByPage(page);
    }

    @Override
    public ArticleWithBLOBs selectArticleBySID(Integer sid) {
        logger.info("进入根据id查询文章方法，文章id： " + sid);
        return articleMapper.selectByPrimaryKey(sid);
    }

}
