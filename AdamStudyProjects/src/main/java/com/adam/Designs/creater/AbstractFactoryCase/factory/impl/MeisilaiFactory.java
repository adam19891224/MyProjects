package com.adam.Designs.creater.AbstractFactoryCase.factory.impl;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;
import com.adam.Designs.creater.AbstractFactoryCase.drink.impl.Coffee;
import com.adam.Designs.creater.AbstractFactoryCase.drink.impl.Milk;
import com.adam.Designs.creater.AbstractFactoryCase.drink.impl.Water;
import com.adam.Designs.creater.AbstractFactoryCase.factory.BreakFristFactory;
import com.adam.Designs.creater.AbstractFactoryCase.food.Food;
import com.adam.Designs.creater.AbstractFactoryCase.food.impl.Bread;
import com.adam.Designs.creater.AbstractFactoryCase.food.impl.Cake;
import com.adam.Designs.creater.AbstractFactoryCase.food.impl.Noodle;

/**
 * 这里定义一个工厂实例，我们就取名为附近的美思来烘焙店，然后根据传入的不同参数，创建不同的实例就好，当然，这里之所以能用一个对象
 * 来代表不同的实例，肯定是采用了声明接口参数来接收实现类的方式，即多态
 * @author Administrator
 *
 */
public class MeisilaiFactory implements BreakFristFactory {

	public Food giveMyFood(String require) {
		if(require.equals("noodle")){
			return  new Noodle();
		}
		if(require.equals("cake")){
			return new Cake();
		}
		if(require.equals("bread")){
			return new Bread();
		}
		return null;
	}

	public Drink giveMyDrink(String require) {
		if(require.equals("water")){
			return new Water();
		}
		if(require.equals("coffee")){
			return new Coffee();
		}
		if(require.equals("milk")){
			return new Milk();
		}
		return null;
	}


}
