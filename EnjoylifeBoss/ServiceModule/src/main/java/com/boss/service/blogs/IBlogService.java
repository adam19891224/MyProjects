package com.boss.service.blogs;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.blog.pojo.ArticleBossPJ;
import com.boss.dao.blog.pojo.ArticlePutObj;
import com.boss.dao.blog.pojo.ArticleWithBLOBs;
import com.boss.foundation.entity.ArticleEntity;
import com.boss.foundation.entity.EnjoyFile;
import com.boss.foundation.view.Page;

import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/12/5
 */
public interface IBlogService {

    String toUploadFile(EnjoyFile file);

    String saveBlog(ArticleEntity entity);

    String updateBlog(ArticleWithBLOBs entity);

    String deleteBlog(Integer sid);

    Page<ArticleBossPJ> selectArticleByPage(Page<ArticleBossPJ> page);

    boolean refresh();

    boolean deleteES();

    List<Article> selectArticlesByTypeID(String id);

    List<Article> selectArticlesWithOutTypeID(String id);

    ArticleWithBLOBs selectArticleByPrimaryKey(Integer key);

    List<Article> selectArticlesBySeriesId(String id);

    List<Article> selectArticlesWithOutSeriesID(String id);

    String saveTags(ArticlePutObj obj);
}
