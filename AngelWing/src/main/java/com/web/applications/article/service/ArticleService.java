package com.web.applications.article.service;

import com.system.webui.Page;
import com.web.applications.article.entity.ArticleWithBLOBs;
import com.web.applications.article.form.ArticleForm;
import com.web.base.service.BaseService;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: adam
 * Date: 2015/11/13
 */
public interface ArticleService extends BaseService<ArticleWithBLOBs> {

    List<ArticleWithBLOBs> selectByPage(Page<ArticleWithBLOBs> page);

    Integer getCounts(Page<ArticleWithBLOBs> page);

    boolean saveArticleByForm(ArticleForm form);

}
