package com.adam.Designs.creater.FactoryMethod.main;

import com.adam.Designs.creater.FactoryMethod.entity.Entity;
import com.adam.Designs.creater.FactoryMethod.facotry.Factory;
import com.adam.Designs.creater.FactoryMethod.facotry.impl.AnimalFactory;
import com.adam.Designs.creater.FactoryMethod.facotry.impl.PlantFactory;

public class Main {

	/**
	 * 工厂方法的调用过程,此时我们就要根据特定的需求来创建不同的工厂(Animal, Plant)来得到对应的实体了
	 * 而不像抽象工厂一样，把各个实体的方法写在工厂中，这样只需要一个工厂实现类就可以创建，而工厂方法不同，它的接口
	 * 只有一个方法，就是创建实体，而剩下的具体创建什么实体，就交给实现它的各个工厂实现类，而这些工厂实现类就组成了一个工厂池
	 */
	public static void main(String[] args) {
		//定义两个entity接口变量
		Entity animal;
		Entity plant;
		
		//定义两个工厂接口变量
		Factory animalFactory;
		Factory plantFactory;
		
		//这里就不能通过工厂来创建工厂实现类了，而是直接创建对应的工厂实现类
		animalFactory = new AnimalFactory();
		plantFactory = new PlantFactory();
		
		//然后把entity变量通过对应的工厂创建实体
		animal = animalFactory.getEntity();
		plant = plantFactory.getEntity();
		
		animal.say();
		plant.say();
		
		/*
		 * 总结：
		 *  工厂方法与抽象工厂不同的是，工厂方法会根据实体的类型，创建对应的工厂实现类，即实体中有animal，则我们要创建对应的animalFactory,实体中有
		 *  plant,我们就同时需要创建对应的plantFactory，此时工厂的接口中就只有一个返回实体的entity方法，而不需要根据实体的不同写多个方法了，
		 *  它充分证明了把创建实体的权利交给它的子类，即animalFacotory和plantFacotry，而其本身并不知道究竟创建什么，只需要返回一个实体接口的getEntity方法
		 *  即可，而到底是返回Animal还是Plant,交给实现它的两个实体工厂来做
		 */
	}

}
