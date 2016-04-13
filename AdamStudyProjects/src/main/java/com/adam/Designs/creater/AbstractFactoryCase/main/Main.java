package com.adam.Designs.creater.AbstractFactoryCase.main;

import com.adam.Designs.creater.AbstractFactoryCase.drink.Drink;
import com.adam.Designs.creater.AbstractFactoryCase.factory.BreakFristFactory;
import com.adam.Designs.creater.AbstractFactoryCase.factory.BreakFristGroupFactory;
import com.adam.Designs.creater.AbstractFactoryCase.factory.impl.McDownloadFactory;
import com.adam.Designs.creater.AbstractFactoryCase.factory.impl.MeisilaiFactory;
import com.adam.Designs.creater.AbstractFactoryCase.food.Food;

public class Main {

	/**
	 * 模拟早餐环境
	 * @param args
	 */
	public static void main(String[] args) {
		
		//imitateBreakfrist();//模拟早餐的基本情况
		
		imitateGroup();//模拟套餐
	}

	private static void imitateGroup() {
		//此时我们模拟套餐效果：
		
		/*
		//今天进肯德基
		BreakFristGroupFactory factory = new KenturyFactory();
		
		Food food = factory.getFood();
		Drink drink = factory.getDrink();
		*/
		
		//今天进麦当劳
		BreakFristGroupFactory factory = new McDownloadFactory();
		Food food = factory.getFood();
		Drink drink = factory.getDrink();
		food.eating();
		drink.drinking();
	}

	/**
	 * 早餐模拟方法
	 */
	@SuppressWarnings("unused")
	private static void imitateBreakfrist() {
		//声明一个早餐工厂对象，其实体就是我们定义的美思莱餐厅
		BreakFristFactory factory = new MeisilaiFactory();
		
		//声明两个早餐食物接口，一个是食物，一个是饮料
		Food food;
		Drink drink;
		
		
		/*
		//假设此时我需要一份面条，一份咖啡，就可以这样：
		food = factory.giveMyFood("noodle");
		drink = factory.giveMyDrink("coffee");
		*/
		
		//如果我们需要一份面包和牛奶，就可以这样：
		food = factory.giveMyFood("bread");
		drink = factory.giveMyDrink("milk");
		
		food.eating();
		drink.drinking();
	}
}
