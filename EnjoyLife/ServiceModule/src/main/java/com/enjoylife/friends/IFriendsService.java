package com.enjoylife.friends;

import com.enjoylife.friends.vo.Friends;
import com.enjoylife.view.Page;

/**
 * ranmin-zhouyuhong
 * 2017/1/19
 */
public interface IFriendsService {

    Page<Friends> selectFriendsByPage(Page<Friends> page);

}
