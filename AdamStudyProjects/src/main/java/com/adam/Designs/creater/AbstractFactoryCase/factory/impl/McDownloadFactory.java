package com.adam.Designs.creater.AbstractFactoryCase.factory.impl;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;
import com.adam.Designs.creater.AbstractFactoryCase.drink.impl.Coffee;
import com.adam.Designs.creater.AbstractFactoryCase.factory.BreakFristGroupFactory;
import com.adam.Designs.creater.AbstractFactoryCase.food.Food;
import com.adam.Designs.creater.AbstractFactoryCase.food.impl.Noodle;

/**
 * 麦当劳的餐厅，该餐厅的套餐是一份面条+一份咖啡
 */
public class McDownloadFactory implements BreakFristGroupFactory {

	public Food getFood() {
		return new Noodle();
	}

	public Drink getDrink() {
		return new Coffee();
	}

}
