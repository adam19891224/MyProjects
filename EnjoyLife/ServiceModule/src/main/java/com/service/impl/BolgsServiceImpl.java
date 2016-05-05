package com.service.impl;

import com.article.domain.ArticleDomain;
import com.article.domain.TypeDomain;
import com.article.vo.ArticleWithBLOBs;
import com.foundation.form.ArticleForm;
import com.foundation.view.Page;
import com.foundation.view.Tree;
import com.service.IBlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/23
 */
@Service
public class BolgsServiceImpl extends BaseServiceImpl implements IBlogsService {

    @Autowired
    private ArticleDomain articleDomain;
    @Autowired
    private TypeDomain typeDomain;

    @Override
    public Page<ArticleWithBLOBs> selectArticlesByPage(Page<ArticleWithBLOBs> page) {
        logger.info("进入查询文章分页方法");
        return articleDomain.selectByPage(page);
    }

    @Override
    public ArticleWithBLOBs selectArticleBySID(Integer sid) {
        return articleDomain.selectBySID(sid);
    }

    @Override
    public void saveArticle(ArticleForm form) {
        logger.info("进入保存文章方法");
        articleDomain.save(form);
    }

    @Override
    public List<Tree> selectTypeToTree() {
        logger.info("进入查询分类树方法");
        return typeDomain.selectToTree();
    }
}
