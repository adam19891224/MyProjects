package com.boss.service.types;

import com.boss.dao.types.pojo.TypesInfo;
import com.boss.foundation.view.Page;

/**
 * ranmin-zhouyuhong
 * 2016/12/13
 */
public interface TypesService {

    Page<TypesInfo> selectTypesByPage(Page<TypesInfo> page);

}
