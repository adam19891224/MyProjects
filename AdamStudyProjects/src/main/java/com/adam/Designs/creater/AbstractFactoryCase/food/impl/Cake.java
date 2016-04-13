package com.adam.Designs.creater.AbstractFactoryCase.food.impl;

import com.adam.Designs.creater.AbstractFactoryCase.food.Food;

/**
 * 实现早餐接口，该类表示蛋糕
 *
 */
public class Cake implements Food {

	public void eating() {
		System.out.println("今天吃蛋糕......");
		System.out.println();
	}

}
