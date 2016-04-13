package com.web.manager.user.util;

public enum Users {

	ADMIN("1b9aca0f-8a2e-4504-9a65-dbf3acc6be8b", "1b9aca0f-8a2e-4504-9a65-dbf3acc6ssq1");
	
	private String userId;

	private String roleId;
	
	private Users(String userId, String roleId){
		this.userId = userId;
		this.roleId = roleId;
	}
	
	public String userId(){
		return this.userId;
	}

	public String roleId(){
		return this.roleId;
	}
}
