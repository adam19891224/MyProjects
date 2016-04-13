package com.adam.Designs.structure.Decorator.interfaces.impl;

import com.adam.Designs.structure.Decorator.interfaces.AbstractEntity;

/**
 * 实际类
 *
 */
public class ConcreteEntity implements AbstractEntity {
	
	private ConcreteEntity(Builder b){
		
	}
	public static class Builder{
		
		public ConcreteEntity builder(){
			return new ConcreteEntity(this);
		}
		
	}

	public void sayHello() {
		System.out.println("你好，世界！");
	}

}
