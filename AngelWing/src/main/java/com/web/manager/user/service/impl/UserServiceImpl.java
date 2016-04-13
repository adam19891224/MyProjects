package com.web.manager.user.service.impl;

import com.web.base.service.impl.BaseServiceImpl;
import com.web.manager.user.dao.UserMapper;
import com.web.manager.user.entity.User;
import com.web.manager.user.service.UserService;
import com.web.manager.user.util.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(String userId) {
		if(userId != null && !userId.trim().isEmpty()){
			return this.userMapper.deleteByPrimaryKey(userId);
		}
		return 0;
	}

	@Override
	public int insertSelective(User record) {
		if(record != null){
			logger.info("进入 新增用户 方法  用户名称：" + record.getUserName());
			return this.userMapper.insertSelective(record);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		if(record != null){
			logger.info("进入 修改密码 方法  用户ID：" + record.getUserId());
			return this.userMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	@Override
	public List<User> selectByMap(Map<String, Object> map) {
		return this.userMapper.selectByMap(map);
	}

	@Override
	public User getAdmin() {
		return this.userMapper.selectByPrimaryKey(Users.ADMIN.userId());
	}

}
