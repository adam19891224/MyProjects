package com.web.manager.resource.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.system.annotation.ControllerLogs;
import com.system.annotation.SubmitToken;
import com.system.webui.Tree;
import com.tools.utils.ConUtils;
import com.tools.utils.StringUtils;
import com.web.base.controller.BaseController;
import com.web.manager.resource.service.ResourceService;
import com.web.manager.resource.util.Resources;
import com.web.manager.role.entity.Role;
import com.web.manager.role.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/manager/resource")
public class ResourceController extends BaseController {

	@Resource
	private RoleService roleService;
	@Resource
	private ResourceService resourceService;
	
	@RequestMapping("/main")
	@ControllerLogs(description = "资源主页")
	public String main(){
		return "manager/resource/main";
	}
	
	@RequestMapping("/add")
	@ControllerLogs(description = "资源添加")
	@SubmitToken(save = true)
	public String add(String id, ModelMap map){
		logger.info("进入 资源添加页面");
		List<Role> rList = roleService.getAllRole();
		map.put("roles", rList);
		com.web.manager.resource.entity.Resource resource = resourceService.selectById(id);
		map.addAttribute("resource", resource);
		return "manager/resource/add";
	}
	
	@RequestMapping("/edit")
	@SubmitToken(save = true)
	@ControllerLogs(description = "资源编辑")
	public String edit(String id, ModelMap map){
		logger.info("进入 资源编辑页面");
		com.web.manager.resource.entity.Resource resource = resourceService.selectById(id);
		map.addAttribute("resource", resource);
		return "manager/resource/edit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	@SubmitToken(remove = true)
	public String update(@ModelAttribute com.web.manager.resource.entity.Resource resource){
		com.web.manager.resource.entity.Resource entity = resourceService.selectById(resource.getResourceId());
		if(entity != null){
			if(StringUtils.isNotNull(entity.getResourceName()) && !entity.getResourceName().equals(resource.getResourceName())){
				entity.setResourceName(resource.getResourceName());
			}
			if(StringUtils.isNotNull(entity.getResourceUrl()) && !entity.getResourceUrl().equals(resource.getResourceUrl())){
				entity.setResourceUrl(resource.getResourceUrl());
			}
			if(!Objects.equals(entity.getResourceOrder(), resource.getResourceOrder())){
				entity.setResourceOrder(resource.getResourceOrder());
			}
			if(!Objects.equals(entity.getResourceIcon(), resource.getResourceIcon())){
				entity.setResourceIcon(resource.getResourceIcon());
			}
			resourceService.updateEntity(entity);
		}
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@SubmitToken(remove = true)
	public String save(@ModelAttribute com.web.manager.resource.entity.Resource resource, ModelMap map){
		if(resource.getParentResourceId().equals("1")){
			resource.setParentResourceId(null);
		}
		resource.setCreateDate(new Date());
		resource.setUpdateDate(new Date());
		resource.setResourceId(UUID.randomUUID().toString());
		if(!StringUtils.isNotNull(resource.getResourceIcon())){
			resource.setResourceIcon(Resources.DefaultIcon.toString());
		}
		resourceService.insertEntity(resource);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String id){
		com.web.manager.resource.entity.Resource resource = resourceService.selectById(id);
		if(resource != null){
			resource.setIsDelete(true);
			resourceService.updateEntity(resource);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/getResouces")
	public Object getResources(){
		List<com.web.manager.resource.entity.Resource> allRes = resourceService.selectAllResource();
		List<Tree> root = ConUtils.arraylist();
		root.add(this.formatList(allRes));
		String json = JSON.toJSONString(root);
		return JSONArray.parse(json);
	}

	//TODO 修改格式
	public Tree formatList(List<com.web.manager.resource.entity.Resource> list){
		Tree tree = new Tree();
		tree.setId("1");
		tree.setText("总资源数");
		tree.setResourceType("0");
		tree.setChildren(ConUtils.arraylist());
		for (com.web.manager.resource.entity.Resource re : list) {
			if(re.getResourceType().equals("1")){
				Tree fTree = new Tree();
				fTree.setId(re.getResourceId());
				fTree.setText(re.getResourceName());
				fTree.setUrl(re.getResourceUrl());
				fTree.setChildren(ConUtils.arraylist());
				fTree.setResourceType("1");
				for(com.web.manager.resource.entity.Resource sre : list){
					if(sre.getResourceType().equals("2") && sre.getParentResourceId().equals(re.getResourceId())){
						Tree sTree = new Tree();
						sTree.setId(sre.getResourceId());
						sTree.setText(sre.getResourceName());
						sTree.setUrl(sre.getResourceUrl());
						sTree.setResourceType("2");
						fTree.getChildren().add(sTree);
					}
				}
				tree.getChildren().add(fTree);
			}
		}
		
		return tree;
	}
	
}
