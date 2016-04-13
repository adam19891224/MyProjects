package com.web.manager.role.controller;

import com.alibaba.fastjson.JSON;
import com.system.annotation.ControllerLogs;
import com.system.webui.Page;
import com.web.base.controller.BaseController;
import com.web.manager.role.entity.Role;
import com.web.manager.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/manager/role")
public class RoleController extends BaseController {
	
	@Resource
	private RoleService roleService;

	private Page<Role> pages = new Page<Role>();
	
	@RequestMapping("/list")
	@ControllerLogs(description = "角色主页")
	public String list(){

		return "manager/role/list";
	}
	
	@RequestMapping("/add")
	@ControllerLogs(description = "角色添加")
	public String add(){
		return "manager/role/add";
	}
	
	@RequestMapping("/edit")
	@ControllerLogs(description = "角色编辑")
	public String edit(){
		return "manager/role/edit";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public String delete(String ids){
		for(String s : ids.split(",")){
			Role role = roleService.selectByPrimaryKey(s);
			role.setIsDelete(true);
		}
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getJson", method = RequestMethod.POST)
	public Object getJson(@ModelAttribute Role role, Integer page, Integer rows){
		pages.setPageSize(rows);
		pages.setPageNumber(page);
		pages = super.getPage(pages, roleService);
		return JSON.toJSON(super.formatMap(pages));
	}
}
