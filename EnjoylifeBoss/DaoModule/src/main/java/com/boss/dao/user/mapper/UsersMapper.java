package com.boss.dao.user.mapper;

import com.boss.dao.user.pojo.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userSid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userSid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}