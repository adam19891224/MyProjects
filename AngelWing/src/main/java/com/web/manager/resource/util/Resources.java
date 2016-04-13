package com.web.manager.resource.util;

public enum Resources {

	DefaultIcon("icon-search");
	
	private String icon;
	
	private Resources(String icon){
		this.icon = icon;
	}
	
	public String toString(){
		return this.icon;
	}
	
}
