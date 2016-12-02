package com.boss.dao.user.mapper;

import com.boss.dao.user.pojo.Users;
import com.boss.foundation.entity.UserInfo;
import com.boss.foundation.form.LoginForm;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userSid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userSid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    UserInfo selectUserInfoByForm(LoginForm form);
}