package com.enjoylife.blogs.impl;

import com.enjoylife.article.dao.ArticleMapper;
import com.enjoylife.article.vo.ArticleTime;
import com.enjoylife.article.vo.ArticleWithBLOBs;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.blogs.IBlogsService;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/23
 */
@Service
public class BlogsServiceImpl extends BaseAbstractClass implements IBlogsService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Page<NewArticle> selectArticlesByPage(Page<NewArticle> page) {
        {
            Date start = new Date();
            List<NewArticle> list = articleMapper.selectByPage(page);
            if(ConUtils.isNotNull(list)){
                page.setResultList(list);
                if(page.getPagination()){
                    int count = articleMapper.selectCountsByPage(page);
                    page.setTotalCounts(count);
                    //总页数计算方法：总记录数 + 每页显示记录数 - 1 的结果 / 每页显示记录数
                    page.setTotalPages((count + page.getPageSize() - 1) / page.getPageSize());
                }
            }else{
                page.setResultList(ConUtils.arraylist());
            }
            Date end = new Date();
            logger.info("【查询文章集合结束】，当前页数: " + page.getPage() + "，总共耗时： " + super.getMsBetweenTwoDate(start, end) + " ms");
        }
        return page;
    }

    @Override
    public int selectArticlesCountsByPage(Page<NewArticle> page) {
        return articleMapper.selectCountsByPage(page);
    }

    @Override
    public ArticleWithBLOBs selectArticleBySID(Integer sid) {
        return articleMapper.selectByPrimaryKey(sid);
    }

    @Override
    public List<ArticleTime> selectTimeGroupByArticle() {
        return articleMapper.selectTimeGroupByArticle();
    }

}
