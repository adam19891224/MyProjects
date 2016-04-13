package com.adam.Designs.creater.FactoryMethodCase.factory.impl;

import com.adam.Designs.creater.FactoryMethodCase.factory.KFC;
import com.adam.Designs.creater.FactoryMethodCase.food.Food;
import com.adam.Designs.creater.FactoryMethodCase.food.impl.Beef;
/**
 * 牛肉实体的工厂
 *
 */
public class NeedBeef implements KFC {

	public Food getFood() {
		return new Beef();
	}

}
