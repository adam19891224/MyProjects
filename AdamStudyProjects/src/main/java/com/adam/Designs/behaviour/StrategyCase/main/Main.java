package com.adam.Designs.behaviour.StrategyCase.main;

import com.adam.Designs.behaviour.StrategyCase.entity.SortEntity;
import com.adam.Designs.behaviour.StrategyCase.strategy.Strategy;
import com.adam.Designs.behaviour.StrategyCase.strategy.impl.Bubbling;
import com.adam.Designs.behaviour.StrategyCase.strategy.impl.Insert;
import com.adam.Designs.behaviour.StrategyCase.strategy.impl.Select;

/**
 * 策略模式作业
 * @author adam
 * 2015.6.4
 */
public class Main {

	public static void main(String[] args) {
		
		//创建策略的三个实际对象
		Strategy bubbling = new Bubbling();
		Strategy select = new Select();
		Strategy insert = new Insert();
		
		//创建排序实体对象
		SortEntity entity = new SortEntity();
		
		//选择用冒泡
		System.out.println("===========================选择冒泡排序法==================================");
		entity.setStrategy(bubbling);
		entity.sort();
		
		//选择选择排序法
		System.out.println("===========================选择选择排序法==================================");
		entity.setStrategy(select);
		entity.sort();
		
		//选择插入排序法
		System.out.println("===========================选择插入排序法==================================");
		entity.setStrategy(insert);
		entity.sort();
		
	}
	
}
