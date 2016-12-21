package com.boss.dao.types.mapper;

import com.boss.dao.types.pojo.ArticleType;
import com.boss.dao.types.pojo.Type;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.view.Page;

import java.util.List;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer typeSid);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer typeSid);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<TypesInfo> selectTypesByPage(Page<TypesInfo> page);

    int selectTypesCountsByPage(Page<TypesInfo> page);

    Type selectTypeById(String id);

    void deleteArticleTypeByEntity(ArticleType type);

    void deleteTypesByArticleId(String id);

    void deleteTypeBySid(Integer id);

}