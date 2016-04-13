package com.article.domain;

import com.article.dao.ArticleMapper;
import com.article.dao.ArticleTypeMapper;
import com.article.vo.ArticleType;
import com.article.vo.ArticleWithBLOBs;
import com.foundation.form.ArticleForm;
import com.foundation.view.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/1/18
 */
@Component
public class ArticleDomain extends ArticleWithBLOBs {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    public Page<ArticleWithBLOBs> selectByPage(Page<ArticleWithBLOBs> page){
        //局部代码块
        {
            List<ArticleWithBLOBs> list = articleMapper.selectByPage(page);
            if(list.size() > 0){
                page.setPage(page.getPage() + 1);
            }
            page.setResultList(list);
        }
        return page;
    }

    public ArticleWithBLOBs selectBySID(Integer sid){
        return articleMapper.selectByPrimaryKey(sid);
    }

    @Transactional
    public void save(ArticleForm form){
        //生成文章对象
        ArticleWithBLOBs article = new ArticleWithBLOBs();
        BeanUtils.copyProperties(form, article);
        article.setArticleId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());
        //生成文章类别对象
        ArticleType at = new ArticleType();
        at.setArticleTypeId(UUID.randomUUID().toString());
        at.setArticleId(article.getArticleId());
        at.setTypeId(form.getTypeId());
        //保存文章
        articleMapper.insertSelective(article);
        //保存文章类别
        articleTypeMapper.insertSelective(at);
    }

}
