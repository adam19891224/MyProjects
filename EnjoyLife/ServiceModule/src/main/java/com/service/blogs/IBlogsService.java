package com.service.blogs;

import com.article.vo.ArticleWithBLOBs;
import com.article.vo.NewArticle;
import com.foundation.form.ArticleForm;
import com.foundation.view.Page;
import com.foundation.view.Tree;

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
     * 根据主键获取entity
     * @param sid
     * @return
     */
    ArticleWithBLOBs selectArticleBySID(Integer sid);

}
