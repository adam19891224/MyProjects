package com.enjoylife.friends.impl;

import com.enjoylife.base.BaseAbstractClass;
import com.enjoylife.friends.IFriendsService;
import com.enjoylife.friends.dao.FriendsMapper;
import com.enjoylife.friends.vo.Friends;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ranmin-zhouyuhong
 * 2017/1/19
 */
@Service
public class FriendsServiceImpl extends BaseAbstractClass implements IFriendsService {

    @Resource
    private FriendsMapper friendsMapper;

    @Override
    public Page<Friends> selectFriendsByPage(Page<Friends> page) {
        List<Friends> list = friendsMapper.selectFriendsListByPage(page);
        if(ConUtils.isNotNull(list)){
            page.setResultList(list);
            if(page.getPagination()){
                int count = friendsMapper.selectFriendsCountsByPage(page);
                page.setTotalCounts(count);
            }
        }else{
            page.setResultList(new ArrayList<>());
        }
        return page;
    }
}
