package com.adam.Designs.creater.FactoryMethodCase.factory.impl;

import com.adam.Designs.creater.FactoryMethodCase.factory.KFC;
import com.adam.Designs.creater.FactoryMethodCase.food.Food;
import com.adam.Designs.creater.FactoryMethodCase.food.impl.Cola;

/**
 * 创建可乐的工厂
 * @author Administrator
 *
 */
public class NeedCola implements KFC {

	public Food getFood() {
		return new Cola();
	}

}
