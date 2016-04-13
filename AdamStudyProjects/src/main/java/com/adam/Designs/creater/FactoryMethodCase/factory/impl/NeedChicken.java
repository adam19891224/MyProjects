package com.adam.Designs.creater.FactoryMethodCase.factory.impl;

import com.adam.Designs.creater.FactoryMethodCase.factory.KFC;
import com.adam.Designs.creater.FactoryMethodCase.food.Food;
import com.adam.Designs.creater.FactoryMethodCase.food.impl.Chicken;

/**
 * 创建鸡肉的工厂
 *
 */
public class NeedChicken implements KFC {

	public Food getFood() {
		return new Chicken();
	}

}
