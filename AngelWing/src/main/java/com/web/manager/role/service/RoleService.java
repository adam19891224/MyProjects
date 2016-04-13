package com.web.manager.role.service;



import com.system.webui.Page;
import com.web.base.service.BaseService;
import com.web.manager.role.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {

	Role selectRoleByUserId(String userid);
	
	Role selectByPrimaryKey(String id);

	List<Role> getAllRole();
	
	Integer getCounts(Page<Role> page);
	
	List<Role> selectByPage(Page<Role> page);
}
