package com.adam.Designs.creater.FactoryMethodCase.food.impl;

import com.adam.Designs.creater.FactoryMethodCase.food.Food;

/**
 * 牛肉实体
 *
 */
public class Beef implements Food {

	public void enjoying() {
		System.out.println("您正在享用的是：牛肉");
		System.out.println();
	}

}
