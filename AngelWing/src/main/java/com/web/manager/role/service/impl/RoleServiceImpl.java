package com.web.manager.role.service.impl;



import com.system.webui.Page;
import com.web.base.service.impl.BaseServiceImpl;
import com.web.manager.role.dao.RoleMapper;
import com.web.manager.role.entity.Role;
import com.web.manager.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role selectRoleByUserId(String userid) {
		return this.roleMapper.selectRoleByUserId(userid);
	}


	@Override
	public List<Role> getAllRole() {
		return this.roleMapper.getAllRole();
	}


	@Override
	public Integer getCounts(Page<Role> page) {
		return this.roleMapper.getCounts(page);
	}


	@Override
	public List<Role> selectByPage(Page<Role> page) {
		return this.roleMapper.selectByPage(page);
	}


	@Override
	public Role selectByPrimaryKey(String id) {
		return this.roleMapper.selectByPrimaryKey(id);
	}

}
