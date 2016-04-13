package com.adam.Designs.behaviour.Strategy.main;

import com.adam.Designs.behaviour.Strategy.entity.MyEntity;
import com.adam.Designs.behaviour.Strategy.strategy.impl.ConStrategy1;
import com.adam.Designs.behaviour.Strategy.strategy.impl.ConStrategy2;

/**
 * 策略模式演示
 * @author adam
 * 2015.6.3
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//创建实体类1，实体类2，其中实体类1调用1策略，实体类2调用2策略
		MyEntity entity1 = new MyEntity();
		MyEntity entity2 = new MyEntity();
		
		entity1.setStrategy(new ConStrategy1());
		entity2.setStrategy(new ConStrategy2());
		
		//调用方法
		entity1.excute();
		entity2.excute();
		
	}
	
	/* 
	 * 总结：
	 * 策略模式就如同上面展示的那样，这里有人会说，策略模式看起来和前面的状态模式差不多啊，那么两者有什么区别吗？
	 * 当然，策略模式是根据不同的实体要求，调用不同的策略实体
	 * 而状态模式是根据实体内部的一个状态值来做事，所以状态内部会有至少一个if-else的判断，而策略模式就不同了，当然，你也可以在策略模式内部用一个状态标识来判断，并调用不同的策略，
	 * 但是这样就不是我们讲解的策略模式了
	 * 
	 */
}
