package com.web.manager.resource.service.impl;

import com.tools.utils.ConUtils;
import com.web.base.service.impl.BaseServiceImpl;
import com.web.manager.resource.dao.ResourceMapper;
import com.web.manager.resource.entity.MenuResource;
import com.web.manager.resource.entity.Resource;
import com.web.manager.resource.service.ResourceService;
import com.web.manager.role.dao.RoleResourceMapper;
import com.web.manager.role.entity.RoleResource;
import com.web.manager.user.util.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public List<Resource> selectResourceByMap(Map<String, Object> map) {
		return resourceMapper.selectResourceByMap(map);
	}

	@Override
	@Transactional
	public void insertEntity(Resource resource) {
		logger.info("调用 插入新资源方法");
		this.resourceMapper.insertSelective(resource);
		RoleResource roleResource = new RoleResource();
		roleResource.setRoleResourceId(UUID.randomUUID().toString());
		roleResource.setResourceId(resource.getResourceId());
		roleResource.setRoleId(Users.ADMIN.roleId());
		this.roleResourceMapper.insertSelective(roleResource);
	}

	@Override
	public Resource selectById(String id) {
		return this.resourceMapper.selectByPrimaryKey(id);
	}
	
	@Override
	@Transactional
	public void updateEntity(Resource resource) {
		this.resourceMapper.updateByPrimaryKeySelective(resource);
	}

	//TODO 修改sql
	@Override
	public List<MenuResource> resourcesToMenu(List<Resource> list) {
		List<MenuResource> menus = ConUtils.arraylist();
		for(com.web.manager.resource.entity.Resource re : list){
			if(re.getResourceType().equals("1")){
				MenuResource menuResource = new MenuResource();
				menuResource.setMenuName(re.getResourceName());
				menuResource.setMenuURL(re.getResourceUrl());
				menuResource.setSunList(ConUtils.arraylist());
				for(com.web.manager.resource.entity.Resource sunRe : list){
					if(sunRe.getResourceType().equals("2") && sunRe.getParentResourceId().equals(re.getResourceId())){
						MenuResource sunMenuResource = new MenuResource();
						sunMenuResource.setMenuName(sunRe.getResourceName());
						sunMenuResource.setMenuURL(sunRe.getResourceUrl());
						sunMenuResource.setMenuIcon(sunRe.getResourceIcon());
						menuResource.getSunList().add(sunMenuResource);
					}
				}
				menus.add(menuResource);
			}
		}
		return menus;
	}

	@Override
	public List<Resource> selectAllResource() {
		return this.resourceMapper.selectAllResources();
	}
}
