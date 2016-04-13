package com.adam.Designs.structure.ProxyCase.proxy;

import com.adam.Designs.structure.ProxyCase.entity.DataBase;

/**
 * 代理对象
 */
public class ProxyDataBase {

	//这是我们的代理对象，其实质就是在数据库保存数据之前，我们向日志中添加一条日志，然后保存之后，又添加一条日志
	
	private DataBase base;
	
	public ProxyDataBase(){
		this.base = new DataBase();
	}
	
	public void save(){
		
		addBeforeLog();
		this.base.save();
		addAfterLog();
		
	}
	
	public void addBeforeLog(){
		System.out.println("写入日志：  即将开始向数据库进项插入操作");
	}
	
	public void addAfterLog(){
		System.out.println("写入日志：  插入操作成功");
	}
	
}
