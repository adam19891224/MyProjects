package com.web.home.login.controller;
/**
 * 登陆控制器
 */
import com.system.annotation.ControllerLogs;
import com.tools.md5.MD5Util;
import com.tools.utils.StringUtils;
import com.web.base.controller.BaseController;
import com.web.manager.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

	/**
	 * 登陆页面
	 */
	@RequestMapping("/login")
	@ControllerLogs(description = "登录")
	public String login(){
		return "home/login/login";
	}
	
	/**
	 * 登出方法
	 */
	@RequestMapping("/logout")
	@ControllerLogs(description = "登出")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.logout();
		}
		return "redirect: login.html";
	}
	
	
	/**
	 * 登录方法验证
	 */
	@ResponseBody
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String dologin(@ModelAttribute User user, HttpServletRequest request){
		logger.info("进入验证登陆信息方法   ");
		String userName = user.getUserName();
		String userPass = MD5Util.string2MD5(user.getUserPassword());
		String checkCode = user.getUserNick();//nick暂时代替验证码
		UsernamePasswordToken token = new UsernamePasswordToken(userName, userPass, checkCode); 
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject(); 
		try {
			currentUser.login(token);
			return "success";
		} catch (Exception e) {
			//获取异常名字，然后进行匹配，如果是password,则说明是密码不正确,这个异常具体定义在shiro的自定义realm中
			if(StringUtils.isEqual(e.getMessage(), "password")){
				logger.error("用户 ： + " + userName + " 密码错误  ");
				return "passwordError";
			}
			//checkcode表示验证码输入错误
			if(StringUtils.isEqual(e.getMessage(), "checkCode")){
				logger.error(" 验证码输入错误  ");
				return "checkCodeError";
			}
			return "error";
		}
	}

	@RequestMapping("/checkCode")
	@ResponseBody
	public String checkCode(String code){
		//得到session的code
		String trueCode = (String) super.getSession().getAttribute("code");
		return String.valueOf(StringUtils.isEqual(code, trueCode));
	}
}
