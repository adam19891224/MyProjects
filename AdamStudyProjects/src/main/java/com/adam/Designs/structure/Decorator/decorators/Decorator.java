package com.adam.Designs.structure.Decorator.decorators;

import com.adam.Designs.structure.Decorator.interfaces.AbstractEntity;

/**
 * 这就是我们的装饰类，它的目的是在不增加abstractEntity接口的方法或者修改代码的前提下
 * 能够自由得扩展它的sayHello方法
 * @author Administrator
 * 2015.5.12
 */
public class Decorator implements AbstractEntity {

	//这里你肯定会说，我们用继承不就完了，但是有些是有往往是不能继承的，例如此时我们的ConcreteEntity是final修饰的，则就不能继承
	//所以我们的解决之道就是在这个装饰类中，声明一个ConcreteEntity对象，然后同样去实现AbstractEntity的方法，只是当装饰类
	//实现接口的sayHello方法时，先调用ConcteteEntity的sayHello方法，然后再做自己的业务
	
	/**
	 * 声明一个concreteEntity
	 */
	public AbstractEntity entity;
	
	public void sayHello() {
		//这是装饰类的sayHello,我们说了，扩展的目的就是在不修改concreteentity的情况下，对sayHello进行扩展
		//所以这里我们先调用concreteEntity的sayHello
		this.entity.sayHello();
		
		//然后实现自己的业务
		System.out.println("你好，我是装饰类");
		
	}

	public static class Builder{
		
		private AbstractEntity e;
		
		public Builder getEntity(AbstractEntity e){
			this.e = e;
			return this;
		}
		
		public Decorator build(){
			return new Decorator(this);
		}
	}
	
	private Decorator(Builder b){
		this.entity = b.e;
	}
	
	
}
