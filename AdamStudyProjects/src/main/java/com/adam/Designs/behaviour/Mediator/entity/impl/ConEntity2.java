package com.adam.Designs.behaviour.Mediator.entity.impl;

import com.adam.Designs.behaviour.Mediator.entity.Entity;
import com.adam.Designs.behaviour.Mediator.mediator.impl.ConMediator;

public class ConEntity2 implements Entity {

	@Override
	public void talk() {
		System.out.println("你好，我是entity2");
	}
	
	public ConEntity2(ConMediator mediator){
		mediator.getList().add(this);
	}

}
