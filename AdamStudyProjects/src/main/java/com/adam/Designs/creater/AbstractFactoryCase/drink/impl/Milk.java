package com.adam.Designs.creater.AbstractFactoryCase.drink.impl;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;

/**
 * 饮料接口实现类，该类表示牛奶
 */
public class Milk implements Drink {

	public void drinking() {
		System.out.println("今天喝牛奶......");
		System.out.println();
	}

}
