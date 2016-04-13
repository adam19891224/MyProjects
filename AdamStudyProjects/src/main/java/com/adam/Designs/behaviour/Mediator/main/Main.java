package com.adam.Designs.behaviour.Mediator.main;

import com.adam.Designs.behaviour.Mediator.entity.Entity;
import com.adam.Designs.behaviour.Mediator.entity.impl.ConEntity1;
import com.adam.Designs.behaviour.Mediator.entity.impl.ConEntity2;
import com.adam.Designs.behaviour.Mediator.mediator.impl.ConMediator;

/**
 * 中介者模式演练
 *
 */
public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//创建中介者
		ConMediator conMediator = new ConMediator();
		//创建两个实体
		Entity entity1 = new ConEntity1(conMediator);
		Entity entity2 = new ConEntity2(conMediator);
		
		//此时两个实体就已经被中介者管理了,我们调用中介者的talk方法
		conMediator.beginTalk();
		
	}
	
	/*
	 * 总结：
	 * 中介者模式就是这样，它可以把一类对象放进自己的一个容器中，统一由这个容器来管理，而各个对象却不用关心太多的事情，这样，对象之间就解耦了
	 *  
	 */
	
}
