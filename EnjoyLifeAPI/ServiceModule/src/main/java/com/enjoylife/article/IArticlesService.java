package com.enjoylife.article;

import com.enjoylife.article.vo.ArticleTime;
import com.enjoylife.article.vo.ArticleWithBLOBs;
import com.enjoylife.article.vo.NewArticle;
import com.enjoylife.view.Page;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/4/23
 */
public interface IArticlesService {

    /**
     * 根据Page对象获取分页信息
     * @param page
     * @return
     */
    Page<NewArticle> selectArticlesByPage(Page<NewArticle> page);

    /**
     * 获取当前总文章数
     */
    int selectArticlesCountsByPage(Page<NewArticle> page);

    /**
     * 根据Page对象获取分页信息
     * @param page
     * @return
     */
    Page<NewArticle> selectTypeArticlesByPage(Page<NewArticle> page);

    /**
     * 获取当前总文章数
     */
    int selectTypeArticlesCountsByPage(Page<NewArticle> page);

    /**
     * 根据主键获取entity
     * @param sid
     * @return
     */
    ArticleWithBLOBs selectArticleBySID(Integer sid);

    /**
     * 根据现有的文章获取时间轴
     * @return
     */
    List<ArticleTime> selectTimeGroupByArticle();
}
