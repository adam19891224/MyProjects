package com.service.blogs;

import com.article.vo.ArticleTime;
import com.article.vo.ArticleWithBLOBs;
import com.article.vo.NewArticle;
import com.foundation.view.Page;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/23
 */
public interface IBlogsService {

    /**
     * 根据Page对象获取分页信息
     * @param page
     * @return
     */
    Page<NewArticle> selectArticlesByPage(Page<NewArticle> page);

    /**
     * 根据Page对象获取分页信息（solr）
     * @param page
     * @return
     */
    Page<NewArticle> selectArticlesByPageSolr(Page<NewArticle> page);

    /**
     * 根据主键获取entity
     * @param sid
     * @return
     */
    ArticleWithBLOBs selectArticleBySID(Integer sid);

    /**
     * 获取前8个热门评论的博客
     */
    List<NewArticle> selectHotsForEight();

    List<ArticleTime> selectTimeGroupByArticle();
}
