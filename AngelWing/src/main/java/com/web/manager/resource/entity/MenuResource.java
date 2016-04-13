package com.web.manager.resource.entity;

import java.util.List;

public class MenuResource {

	private String menuName;
	
	private String menuURL;
	
	private String menuIcon;
	
	private List<MenuResource> sunList;
	
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public List<MenuResource> getSunList() {
		return sunList;
	}

	public void setSunList(List<MenuResource> sunList) {
		this.sunList = sunList;
	}
	
}
