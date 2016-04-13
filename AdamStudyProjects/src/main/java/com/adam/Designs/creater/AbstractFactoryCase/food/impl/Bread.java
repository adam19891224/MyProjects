package com.adam.Designs.creater.AbstractFactoryCase.food.impl;

import com.adam.Designs.creater.AbstractFactoryCase.food.Food;

/**
 * 实现早餐接口，该类表示面包
 */
public class Bread implements Food {

	public void eating() {
		System.out.println("今天吃面包......");
		System.out.println();
	}

}
