package com.enjoylife.type;

import com.enjoylife.type.vo.Type;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Adam
 * Date: 2016/6/2
 */
public interface ITypeService {

    List<Type> selectAllTypes();

    Type selectTypeByArticleId(String articleID);

    int selectAllTypesCount();
}
