package com.boss.service.types.impl;

import com.boss.dao.types.mapper.ArticleTypeMapper;
import com.boss.dao.types.mapper.TypeMapper;
import com.boss.dao.types.pojo.ArticleType;
import com.boss.dao.types.pojo.Type;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.view.Page;
import com.boss.service.base.AbstractService;
import com.boss.service.types.ITypesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
@Service
public class ITypesServiceImpl extends AbstractService implements ITypesService {

    @Resource
    private TypeMapper typeMapper;
    @Resource
    private ArticleTypeMapper articleTypeMapper;

    @Override
    public Page<TypesInfo> selectTypesByPage(Page<TypesInfo> page) {

        List<TypesInfo> result = typeMapper.selectTypesByPage(page);
        if(ConUtils.isNotNull(result)){
            int count = typeMapper.selectTypesCountsByPage(page);
            page.setTotalCounts(count);
            page.setResultList(result);
            page.setSuccess(true);
        }

        return page;
    }

    @Override
    public String saveTypeInfo(Type type) {
        String id = UUID.randomUUID().toString();
        type.setTypeId(id);
        typeMapper.insertSelective(type);
        return "success";
    }

    @Override
    public Type selectTypeBySid(Integer sid) {
        return typeMapper.selectByPrimaryKey(sid);
    }

    @Override
    public Type selectTypeById(String id) {
        return typeMapper.selectTypeById(id);
    }

    @Override
    public String addArticleByType(ArticleType type) {
        try {
            articleTypeMapper.insertSelective(type);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }
        return "error";
    }

    @Override
    public String deleteArticleByType(ArticleType type) {
        try {
            typeMapper.deleteArticleTypeByEntity(type);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }
        return "error";
    }

    @Override
    @Transactional
    public String deleteTypesSafeBySid(Integer id) {

        try {
            typeMapper.deleteTypeBySid(id);
            typeMapper.deleteByPrimaryKey(id);
            return "success";
        }catch (Exception e){
            logger.error("保存关系发生错误", e);
        }

        return "error";
    }

}
