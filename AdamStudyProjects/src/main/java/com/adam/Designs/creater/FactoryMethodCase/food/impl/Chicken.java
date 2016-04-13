package com.adam.Designs.creater.FactoryMethodCase.food.impl;

import com.adam.Designs.creater.FactoryMethodCase.food.Food;


/**
 * 具体食物，这是鸡肉
 *
 */
public class Chicken implements Food {

	public void enjoying() {
		System.out.println("您正在享用的是：鸡肉");
		System.out.println();
	}

}
