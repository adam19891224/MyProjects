package com.adam.Designs.creater.FactoryMethodCase.main;

import com.adam.Designs.creater.FactoryMethodCase.factory.KFC;
import com.adam.Designs.creater.FactoryMethodCase.factory.impl.NeedBeef;
import com.adam.Designs.creater.FactoryMethodCase.factory.impl.NeedChicken;
import com.adam.Designs.creater.FactoryMethodCase.factory.impl.NeedCola;
import com.adam.Designs.creater.FactoryMethodCase.food.Food;

public class Main {

	/**
	 * 开始模拟KFC餐厅的情景
	 * @param args
	 */
	public static void main(String[] args) {

		//创建一个kfc的接口类，这里就取名为waiter
		KFC waiter;
		
		//创建两个需求
		Food customer1;
		Food customer2;
		
		//假设第一个客户需要牛肉，他得到一份牛肉的过程是叫waiter，说他需要一份牛肉，
		//然后服务器告诉厨师，等待之后，厨师把牛肉给服务员，此时服务员就有了一份牛肉
		//最后给客户
		waiter = customerRequire(new NeedBeef());//waiter得到需求，即需要一份牛肉
		customer1 = waiter.getFood();//waiter告诉厨师之后，得到牛肉，交给客户
		customer1.enjoying();//客户开始享用
		
		waiter = customerRequire(new NeedCola());//此时服务员又得到一个需求，这时客户需要一杯可乐
		customer2 = waiter.getFood();//服务员拿到可乐之后，交付给客户
		customer2.enjoying();//客户开始享用
		
		/*
		 * 总结:
		 * 这就是工厂方法模式了，同样，工厂中只有一个getFood的方法，而到底这个方法返回的是什么，就根据后面的需求，创建不同的need工厂实现类，然后通过
		 * 该工厂实现类来创建与之对应的实体类，即把创建实体的行为交给KFC工厂的实现类NeedBeef,NeedChicken,NeedCola三个类来创建
		 * 
		 */
	}
	
	/**
	 * 为了更人性化一点，我们定义一个方法，名字叫顾客需求，返回的还是kfc的接口,需要传一个参数，即顾客需要的食物
	 */
	public static KFC customerRequire(KFC need){
		if(need instanceof NeedBeef){
			return new NeedBeef();
		}
		if(need instanceof NeedChicken){
			return new NeedChicken();
		}
		if(need instanceof NeedCola){
			return new NeedCola();
		}
		return null;
	}

}
