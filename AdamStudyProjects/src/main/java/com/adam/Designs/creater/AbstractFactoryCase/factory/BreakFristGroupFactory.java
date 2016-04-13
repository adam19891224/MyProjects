package com.adam.Designs.creater.AbstractFactoryCase.factory;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;
import com.adam.Designs.creater.AbstractFactoryCase.food.Food;

/**
 * 此时我们灵活运用组合，这个接口表示实现该接口的餐厅实例，会推出自己的套餐
 * @author adam
 *
 */
public interface BreakFristGroupFactory {

	public Food getFood();
	
	public Drink getDrink();
}
