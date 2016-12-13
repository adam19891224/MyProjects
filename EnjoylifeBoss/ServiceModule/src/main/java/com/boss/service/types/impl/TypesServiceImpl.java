package com.boss.service.types.impl;

import com.boss.dao.types.mapper.TypeMapper;
import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.view.Page;
import com.boss.service.types.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypeMapper typeMapper;

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
}
