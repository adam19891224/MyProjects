package com.boss.service.types;

import com.boss.dao.blog.pojo.Article;
import com.boss.dao.types.pojo.Type;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.view.Page;

import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
public interface TypesService {

    Page<TypesInfo> selectTypesByPage(Page<TypesInfo> page);

    String saveTypeInfo(Type type);

    Type selectTypeBySid(Integer sid);

    List<Article> selectArticlesByTypeID(String id);
}
