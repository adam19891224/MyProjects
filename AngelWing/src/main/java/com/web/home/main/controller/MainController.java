package com.web.home.main.controller;

import com.alibaba.fastjson.JSON;
import com.system.annotation.ControllerLogs;
import com.tools.utils.ConUtils;
import com.web.base.controller.BaseController;
import com.web.manager.resource.entity.MenuResource;
import com.web.manager.resource.service.ResourceService;
import com.web.manager.user.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 首页
 * @author Adam
 * 2015.6.4
 *
 */
@Controller
public class MainController extends BaseController {
	
	@Resource
	private ResourceService resourceService;
	
	
	/**
	 * 进入首页
	 */
	@RequestMapping("/main")
	@ControllerLogs(description = "进入主页")
	public String main(ModelMap map){
		User user = super.getCurrentUser();
		map.addAttribute("user", user);
		logger.info(" 用户 : " + user.getUserName() + " 登陆 主页");
		return "main";
	}
	
	/**
	 * 404
	 */
	@RequestMapping("/notfound")
	public String notfound(){
		return "home/notfound";
	}

	/**
	 * 根据登录用户加载左边导航条
	 */
	@ResponseBody
	@RequestMapping(value = "/loadWest", method = RequestMethod.GET)
	public String loadWest(){
		User user = super.getCurrentUser();
		Map<String, Object> mapE = ConUtils.hashmap();
		mapE.put("userId", user.getUserId());
		//根据用户找到对应角色，然后根据角色找到所有该用户的资源
		List<com.web.manager.resource.entity.Resource> resources = resourceService.selectResourceByMap(mapE);
		//创建一个存放节点的集合
		List<MenuResource> menus = resourceService.resourcesToMenu(resources);
		//此时rootMap就包含了所有的资源，然后封装成json即可
		return JSON.toJSONString(menus);
	}
	
}
