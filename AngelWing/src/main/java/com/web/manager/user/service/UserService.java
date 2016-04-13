package com.web.manager.user.service;

import com.web.base.service.BaseService;
import com.web.manager.user.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User> {

	int deleteByPrimaryKey(String userId);
	
	int insertSelective(User record);
	
	int updateByPrimaryKeySelective(User record);
	
	List<User> selectByMap(Map<String, Object> map);
	
	User getAdmin();
}
