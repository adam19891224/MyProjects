package com.enjoylife.utils;

import com.enjoylife.models.ArticleEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2016/11/25
 */
public class EsResultCastUtils {

    public static ArticleEntity getEntityByMap(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {
        ArticleEntity temp = new ArticleEntity();
        Field[] fields = ArticleEntity.class.getDeclaredFields();
        for(Field f : fields){
            BeanUtils.populate(temp, map);
        }
        return temp;
    }

}
