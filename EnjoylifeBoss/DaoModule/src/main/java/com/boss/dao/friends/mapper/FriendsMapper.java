package com.boss.dao.friends.mapper;

import com.boss.dao.friends.pojo.Friends;
import com.boss.foundation.view.Page;

import java.util.List;

public interface FriendsMapper {
    int deleteByPrimaryKey(Integer friendSid);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Integer friendSid);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    List<Friends> selectFriendsListByPage(Page<Friends> page);

    int selectFriendsListCountByPage(Page<Friends> page);
}