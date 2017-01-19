package com.boss.web.friends.controller;

import com.boss.dao.friends.pojo.Friends;
import com.boss.foundation.view.Page;
import com.boss.service.friends.IFriendService;
import com.boss.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ranmin-zhouyuhong
 * 2017/1/19
 */
@Controller
@RequestMapping("/friends")
public class FriendsController extends BaseController{

    @Resource
    private IFriendService friendService;

    @RequestMapping("/list.html")
    public String list(ModelMap map){
        return "friends/list";
    }

    @RequestMapping("/datas.html")
    @ResponseBody
    public String datas(Page<Friends> page, ModelMap map){
        page = friendService.selectFriendsByPage(page);
        return super.castPageToResultString(page);
    }

    @RequestMapping("/add.html")
    public String add(ModelMap map){
        return "friends/index";
    }

    @RequestMapping("/save.html")
    @ResponseBody
    public String save(Friends friends){
        try {
            return friendService.saveFriends(friends);
        }catch (Exception e){
            logger.error("保存标签失败: ", e);
        }
        return "error";
    }

    @RequestMapping("/delete.html")
    @ResponseBody
    public String delete(Integer id){
        try {
            return friendService.deleteFriends(id);
        }catch (Exception e){
            logger.error("删除标签失败: ", e);
        }
        return "error";
    }
}
