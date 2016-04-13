package com.adam.Designs.creater.AbstractFactoryCase.drink.impl;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;

/**
 * 饮料接口实现类，该类表示白开水
 *
 */
public class Water implements Drink {

	public void drinking() {
		System.out.println("今天喝白开水......");
		System.out.println();
	}

}
