package com.adam.Designs.creater.FactoryMethodCase.food.impl;

import com.adam.Designs.creater.FactoryMethodCase.food.Food;

/**
 * 可乐实体
 *
 */
public class Cola implements Food {

	public void enjoying() {
		System.out.println("您正在享用的是：可乐");
		System.out.println();
	}

	
}
