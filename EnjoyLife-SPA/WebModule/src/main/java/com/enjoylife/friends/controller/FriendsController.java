package com.enjoylife.friends.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.ResponseEnum;
import com.enjoylife.friends.IFriendsService;
import com.enjoylife.friends.vo.Friends;
import com.enjoylife.utils.ConUtils;
import com.enjoylife.view.Page;
import com.enjoylife.view.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ranmin-zhouyuhong
 * 2017/1/18
 */
@RestController
public class FriendsController extends BaseController{

    @Resource
    private IFriendsService friendsService;

    @RequestMapping(value = "/friends", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseData<Map<String, Object>> friends(){
        Map<String, Object> map = ConUtils.hashmap();

        //获取所有的友链
        Page<Friends> page = new Page<Friends>();
        page.setPage(1);
        page.setPagination(false);
        page = friendsService.selectFriendsByPage(page);
        map.put("friends", page.getResultList());

        return super.responseRes(ResponseEnum.SUCCESS, map);
    }

}
