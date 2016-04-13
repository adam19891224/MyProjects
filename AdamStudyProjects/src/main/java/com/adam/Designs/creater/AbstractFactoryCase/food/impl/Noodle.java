package com.adam.Designs.creater.AbstractFactoryCase.food.impl;

import com.adam.Designs.creater.AbstractFactoryCase.food.Food;

/**
 * 实现早餐食物接口，该类表示面条
 *
 */
public class Noodle implements Food {

	public void eating() {
		System.out.println("今天吃面条......");
		System.out.println();
	}

}
