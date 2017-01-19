package com.boss.service.friends;

import com.boss.dao.friends.pojo.Friends;
import com.boss.foundation.view.Page;

/**
 * ranmin-zhouyuhong
 * 2017/1/19
 */
public interface IFriendService{

    Page<Friends> selectFriendsByPage(Page<Friends> page);

    String saveFriends(Friends friends);

    String deleteFriends(Integer id);
}
