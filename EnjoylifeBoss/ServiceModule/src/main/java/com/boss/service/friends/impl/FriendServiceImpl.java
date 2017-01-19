package com.boss.service.friends.impl;

import com.boss.dao.friends.mapper.FriendsMapper;
import com.boss.dao.friends.pojo.Friends;
import com.boss.foundation.utils.ConUtils;
import com.boss.foundation.view.Page;
import com.boss.service.base.AbstractService;
import com.boss.service.friends.IFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * ranmin-zhouyuhong
 * 2017/1/19
 */
@Service
public class FriendServiceImpl extends AbstractService implements IFriendService {

    @Resource
    private FriendsMapper friendsMapper;

    @Override
    public Page<Friends> selectFriendsByPage(Page<Friends> page) {
        List<Friends> result = friendsMapper.selectFriendsListByPage(page);
        if(ConUtils.isNotNull(result)){
            int count = friendsMapper.selectFriendsListCountByPage(page);
            page.setTotalCounts(count);
            page.setResultList(result);
            page.setSuccess(true);
        }
        return page;
    }

    @Override
    public String saveFriends(Friends friends) {
        String uuid = UUID.randomUUID().toString();
        friends.setFriendId(uuid);
        friendsMapper.insertSelective(friends);
        return "success";
    }

    @Override
    public String deleteFriends(Integer id) {
        friendsMapper.deleteByPrimaryKey(id);
        return "success";
    }
}
