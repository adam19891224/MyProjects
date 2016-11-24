package com.enjoylife.blogs.impl;

import com.enjoylife.article.vo.ArticleEntity;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.blogs.IBlogsESService;
import com.enjoylife.blogs.repository.IBlogESRepository;
import com.enjoylife.view.Page;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/11/23
 */
@Service
public class BlogsESServiceImpl implements IBlogsESService {

    @Autowired
    private IBlogESRepository blogESRepository;

    @Override
    public List<ArticleEntity> selectArticlesByPage(Page<NewArticle> page) {
        org.springframework.data.domain.Page<ArticleEntity> resP = this.blogESRepository.findArticlesByArticleTitle(page.getKw(), new PageRequest(page.getEsPage(), page.getPageSize()));
        Iterator<ArticleEntity> iterator = resP.iterator();
        return Lists.newArrayList(iterator);
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
