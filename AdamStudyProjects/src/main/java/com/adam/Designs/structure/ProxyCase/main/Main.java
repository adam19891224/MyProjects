package com.adam.Designs.structure.ProxyCase.main;

import com.adam.Designs.structure.ProxyCase.proxy.ProxyDataBase;

/**
 * 代理模式作业演示
 * 2015.5.19
 */
public class Main {

	public static void main(String[] args) {
		
		//创建代理数据库
		ProxyDataBase dataBase = new ProxyDataBase();
		
		//调用插入操作方法
		dataBase.save();
		
	}
	
}
