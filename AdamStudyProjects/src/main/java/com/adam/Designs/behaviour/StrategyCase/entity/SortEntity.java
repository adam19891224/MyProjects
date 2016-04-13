package com.adam.Designs.behaviour.StrategyCase.entity;

import com.adam.Designs.behaviour.StrategyCase.strategy.Strategy;

/**
 * 排序实际类
 *
 */
public class SortEntity {

	//定义排序策略接口声明
	private Strategy strategy = null;
	
	//传入排序策略实体类
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	//开始排序
	public void sort(){
		this.strategy.excute();
	}
	
}
