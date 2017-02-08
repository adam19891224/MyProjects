package com.enjoylife.friends.controller;

import com.enjoylife.base.controller.BaseController;
import com.enjoylife.enums.YesNoTypeEnum;
import com.enjoylife.friends.IFriendsService;
import com.enjoylife.friends.vo.Friends;
import com.enjoylife.view.Page;
import freemarker.template.TemplateException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ranmin-zhouyuhong
 * 2017/1/18
 */
@RestController
public class FriendsController extends BaseController{

    @Resource
    private IFriendsService friendsService;

    @RequestMapping(value = "/friends", method = {RequestMethod.GET, RequestMethod.POST})
    public String friends(ModelMap map, HttpServletRequest request){

        map.addAttribute("isFriends", YesNoTypeEnum.Yes.getCode());

        //查询总文章数和总分类数给前台展示
        super.getTotalTypesToMap(map);
        super.getTotalArticlesToMap(map);

        map.addAttribute("dataType", "friends");

        //获取所有的友链
        Page<Friends> page = new Page<Friends>();
        page.setPage(1);
        page.setPagination(false);
        page = friendsService.selectFriendsByPage(page);
        map.addAttribute("friends", page.getResultList());
        try {
            return toPjax(request, map, "friends");
        } catch (TemplateException | IOException e) {
            logger.error("pjax返回错误");
        }

        return "/error";
    }

}
