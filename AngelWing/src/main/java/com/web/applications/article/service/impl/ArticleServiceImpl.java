package com.web.applications.article.service.impl;

import com.system.webui.Page;
import com.tools.utils.UploadUtils;
import com.web.applications.article.dao.ArticleMapper;
import com.web.applications.article.dao.ArticleTypeMapper;
import com.web.applications.article.entity.ArticleType;
import com.web.applications.article.entity.ArticleWithBLOBs;
import com.web.applications.article.form.ArticleForm;
import com.web.applications.article.service.ArticleService;
import com.web.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/13
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleWithBLOBs> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Override
    public List<ArticleWithBLOBs> selectByPage(Page<ArticleWithBLOBs> page) {
        return articleMapper.selectByPage(page);
    }

    @Override
    public Integer getCounts(Page<ArticleWithBLOBs> page) {
        return articleMapper.getCounts(page);
    }

    @Override
    @Transactional
    public boolean saveArticleByForm(ArticleForm form) {

        ArticleWithBLOBs article = new ArticleWithBLOBs();
        article.setArticleId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());
        article.setArticleTitle(form.getArticleTitle());
        article.setArticleImg(form.getArticleImage());
        article.setArticleDescription(form.getArticleDesc());
        article.setArticleBody(form.getArticleBody());

        ArticleType type = new ArticleType();
        type.setArticleTypeId(UUID.randomUUID().toString());
        type.setArticleId(article.getArticleId());
        type.setTypeId(form.getTypeID());

        articleMapper.insertSelective(article);
        articleTypeMapper.insertSelective(type);

        return true;
    }
}
