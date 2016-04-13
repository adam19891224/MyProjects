package com.adam.Designs.behaviour.Strategy.entity;

import com.adam.Designs.behaviour.Strategy.strategy.Strategy;

/**
 * 用户实体类
 *
 */
public class MyEntity {

	//定义一个策略
	private Strategy strategy = null;
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	public void excute(){
		this.strategy.excute();
	}
	
}
