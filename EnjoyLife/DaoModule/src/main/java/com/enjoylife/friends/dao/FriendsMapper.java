package com.enjoylife.friends.dao;

import com.enjoylife.friends.vo.Friends;
import com.enjoylife.view.Page;

import java.util.List;

public interface FriendsMapper {
    int deleteByPrimaryKey(Integer friendSid);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Integer friendSid);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    List<Friends> selectFriendsListByPage(Page<Friends> page);

    int selectFriendsCountsByPage(Page<Friends> page);
}