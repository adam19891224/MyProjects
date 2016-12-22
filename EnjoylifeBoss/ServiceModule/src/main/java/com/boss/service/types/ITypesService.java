package com.boss.service.types;

import com.boss.dao.types.pojo.ArticleType;
import com.boss.dao.types.pojo.Type;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.view.Page;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
public interface ITypesService {

    Page<TypesInfo> selectTypesByPage(Page<TypesInfo> page);

    String saveTypeInfo(Type type);

    Type selectTypeBySid(Integer sid);

    Type selectTypeById(String id);

    String addArticleByType(ArticleType type);

    String deleteArticleByType(ArticleType type);

    String deleteTypesSafeBySid(Integer id);
}
