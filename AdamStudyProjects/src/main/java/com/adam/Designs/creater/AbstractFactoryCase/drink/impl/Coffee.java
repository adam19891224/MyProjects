package com.adam.Designs.creater.AbstractFactoryCase.drink.impl;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;

/**
 * 饮料接口实现类，该类表示咖啡
 */
public class Coffee implements Drink {

	public void drinking() {
		System.out.println("今天喝咖啡......");
		System.out.println();
	}

}
