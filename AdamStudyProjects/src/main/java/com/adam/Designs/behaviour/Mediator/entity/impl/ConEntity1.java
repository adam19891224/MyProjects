package com.adam.Designs.behaviour.Mediator.entity.impl;

import com.adam.Designs.behaviour.Mediator.entity.Entity;
import com.adam.Designs.behaviour.Mediator.mediator.impl.ConMediator;

/**
 * 实现类
 */
public class ConEntity1 implements Entity {
	
	//构造方法，需要传入中介者，同时把自己放入中介者的管理集合中
	public ConEntity1(ConMediator mediator){
		mediator.getList().add(this);
	}

	@Override
	public void talk() {
		System.out.println("你好，我是entity1");
	}

}
