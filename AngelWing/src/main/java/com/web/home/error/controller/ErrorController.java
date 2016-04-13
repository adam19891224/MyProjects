package com.web.home.error.controller;

import com.web.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误控制器
 */
@Controller
public class ErrorController extends BaseController {

	/**
	 * 返回错误页面
	 */
	@RequestMapping("/error")
	public String error(){
		logger.error("进入错误页面");
		return "home/error/error";
	}
	
}
