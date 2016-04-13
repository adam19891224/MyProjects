package com.adam.Designs.creater.FactoryMethodCase.factory;

import com.adam.Designs.creater.FactoryMethodCase.food.Food;

/**
 * 工厂方法实例模拟，同样这里采用KFC餐厅的情况来模拟
 * 这个餐厅只有一个方法，根据顾客的需要，给他们食物
 * @author adam
 *
 */
public interface KFC {

	public Food getFood();
	
}
