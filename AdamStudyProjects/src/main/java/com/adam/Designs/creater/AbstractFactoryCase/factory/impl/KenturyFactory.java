package com.adam.Designs.creater.AbstractFactoryCase.factory.impl;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;
import com.adam.Designs.creater.AbstractFactoryCase.drink.impl.Milk;
import com.adam.Designs.creater.AbstractFactoryCase.factory.BreakFristGroupFactory;
import com.adam.Designs.creater.AbstractFactoryCase.food.Food;
import com.adam.Designs.creater.AbstractFactoryCase.food.impl.Bread;

/**
 * 肯德基餐厅，实现了早餐工程的接口，肯德基餐厅的套餐是一份面包+一份牛奶
 *
 */
public class KenturyFactory implements BreakFristGroupFactory {

	/*
	 * 肯德基的套餐，一份牛奶+一份面包
	 * @see com.adam.Designs.AbstractFactoryCase.breakfristFactory.BreakFristGroupFactory#getFood()
	 */
	public Food getFood() {
		return new Bread();
	}

	public Drink getDrink() {
		return new Milk();
	}


}
