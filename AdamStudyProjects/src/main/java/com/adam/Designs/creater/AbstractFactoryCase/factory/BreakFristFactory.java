package com.adam.Designs.creater.AbstractFactoryCase.factory;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;
import com.adam.Designs.creater.AbstractFactoryCase.food.Food;

/**
 * 抽象工厂实际案例，这里以早餐这个形式来模拟一个早餐工厂，工厂只有两个方法，一个是创建得到食物接口，另一个是得到饮料接口
 * 至于到底要什么，根据客户的要求自己选择即可
 * @author adam
 *
 */
public interface BreakFristFactory {

	public Food giveMyFood(String require);
	
	public Drink giveMyDrink(String require);
}
