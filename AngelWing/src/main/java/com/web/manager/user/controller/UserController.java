package com.web.manager.user.controller;

import com.system.annotation.ControllerLogs;
import com.system.annotation.SubmitToken;
import com.tools.md5.MD5Util;
import com.tools.utils.StringUtils;
import com.web.base.controller.BaseController;
import com.web.manager.user.entity.User;
import com.web.manager.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/manager/user")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService;

	/**
	 * 进入修改密码页面
	 * @param map
	 * @return
     */
	@RequestMapping("/syspassword")
	@ControllerLogs(description = "系统用户密码修改")
	@SubmitToken(save = true)//开启生成token注解
	public String syspassword(ModelMap map){
		logger.info("进入 修改密码页面");
		User admin = userService.getAdmin();
		map.addAttribute("admin", admin);
		return "manager/user/syspassword";
	}

	/**
	 * 异步修改密码
	 * @param user
	 * @return
     */
	@ResponseBody
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	@SubmitToken(remove = true)//开启验证重复提交注解
	public String updatepassword(@ModelAttribute User user){
		logger.info("进入 修改密码方法  ");
		User admin = userService.getAdmin();
		if(StringUtils.isNotNull(user.getUserName()) && !user.getUserName().equals(admin.getUserName())){
			admin.setUserName(user.getUserName());
		}
		admin.setUserPassword(MD5Util.string2MD5(user.getUserPassword()));
		userService.updateByPrimaryKeySelective(admin);
		return "success";
	}

	/**
	 * 验证历史密码是否正确
	 * @param password
	 * @return
     */
	@ResponseBody
	@RequestMapping(value = "/checkpassword", method = RequestMethod.POST)
	public String checkpassword(String password){
		password = MD5Util.string2MD5(password);
		User admin = userService.getAdmin();
		if(StringUtils.isEqual(password, admin.getUserPassword())){
			return "success";
		}
		return "error";
	}
	
}
