package com.adam.Designs.structure.Decorator.main;

import com.adam.Designs.structure.Decorator.decorators.Decorator;
import com.adam.Designs.structure.Decorator.interfaces.AbstractEntity;
import com.adam.Designs.structure.Decorator.interfaces.impl.ConcreteEntity;

/**
 * 装饰模式的作业
 * @author adam
 * 2015.5.12
 */
public class Main {

	public static void main(String[] args) {
		
		//创建concreteEntity类
		AbstractEntity cont = new ConcreteEntity.Builder().builder();
		cont.sayHello();
		
		//创建装饰类
		AbstractEntity deco = new Decorator.Builder().getEntity(cont).build();
		deco.sayHello();
		
		/*
		 * 总结
		 * 我们发现，我们在这里并没有对concreteEntity做任何事情，就实现了调用ConcreteEntity的sayHello方法的同时（你好，世界），又实现了我们
		 * 自己的逻辑（你好，我是装饰类）
		 * 当然，这种模式并不是唯一解决这类问题的答案，例如继承concreteEntity为父类，那么子类也能有sayHello方法，则到时候只需在子类的sayHello方法内部
		 * 调用父类的sayHello(super.sayHello())方法即可
		 * 但是有些时候并没有我们想象的顺利，例如此时concreteEntity是final的对象，那么就不允许被继承了，所以用装饰模式就能很好的解决这个问题
		 */
		
	}
	
}
