package com.adam.Designs.creater.Builder.builder.impl;

import com.adam.Designs.creater.Builder.builder.Builder;
import com.adam.Designs.creater.Builder.entity.Entity;

/**
 * 创建者接口的实现类
 *
 */
public class ConcreteBuilder implements Builder {
	
	//建立一个构造方法
	private Entity e;
	
	public ConcreteBuilder(){
		this.e = new Entity();
	}

	//设置它的颜色
	public void setColor(String type) {
		this.e.setColor(type);
	}

	public void setSize(String type) {
		this.e.setSize(type);
	}

	//返回该对象
	public Entity getEntity() {
		return this.e;
	}

}
