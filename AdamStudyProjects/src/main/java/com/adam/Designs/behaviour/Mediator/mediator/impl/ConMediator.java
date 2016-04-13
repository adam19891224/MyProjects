package com.adam.Designs.behaviour.Mediator.mediator.impl;

import java.util.List;

import com.adam.Designs.behaviour.Mediator.entity.Entity;
import com.adam.Designs.behaviour.Mediator.mediator.Mediator;
import com.adam.Utils.Utils;

/**
 * 中介者实现类
 *
 */
public class ConMediator implements Mediator {

	//声明一个entity集合，作为统一对象的中介平台
	private List<Entity> list = Utils.getList();

	@Override
	public void beginTalk() {
		for(Entity entity : this.list){
			entity.talk();
		}
	}

	//对外提供得到中介平台的方法，好让外界能够把自己放入这个平台让中介者管理
	public List<Entity> getList() {
		return list;
	}
	
}
