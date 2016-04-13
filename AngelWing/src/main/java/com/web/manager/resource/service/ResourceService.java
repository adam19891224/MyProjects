package com.web.manager.resource.service;

import com.web.base.service.BaseService;
import com.web.manager.resource.entity.MenuResource;
import com.web.manager.resource.entity.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceService extends BaseService<Resource> {

	List<Resource> selectResourceByMap(Map<String, Object> map);
	
	List<Resource> selectAllResource();
	
	List<MenuResource> resourcesToMenu(List<Resource> list);
	
	void insertEntity(Resource resource);
	
	Resource selectById(String id);
	
	void updateEntity(Resource resource);
}
