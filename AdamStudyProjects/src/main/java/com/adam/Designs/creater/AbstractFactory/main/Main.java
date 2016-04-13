package com.adam.Designs.creater.AbstractFactory.main;

import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityA;
import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityB;
import com.adam.Designs.creater.AbstractFactory.factory.AbstractFactory;
import com.adam.Designs.creater.AbstractFactory.factory.impl.ConcreteFactory;

/**
 * 抽象工厂使用简介
 */
public class Main {

	public static void main(String[] args) {
		/**
		 * 首先声明两个接口参数
		 */
		AbstractEntityA entityA;
		AbstractEntityB entityB;
		
		/**
		 * 然后声明一个抽象工厂参数
		 */
		AbstractFactory factory;
		
		//通过多态的方式，得到工厂实例，即变量是Abstract类型，但是实际创建的是Concrete类型的对象
		factory = new ConcreteFactory();
		
		//通过工厂的两个方法创建两个实体对象，注意，这里采用了多态，即虽然我们定义的是abstract的变量，但是实际接受的是concrete类型的返回值
		entityA = factory.getEntityA();
		entityB = factory.getEntityB();
		
		//调用两个对象的方法
		entityA.sayHello();
		entityB.showMy();
		
		
		/*
		 * 所以从这里我们可以看出，如果我们是用户的状态或者使用者的状态，我们做的事情就如上面展示的一样：
		 * 1.创建两个对象接口的参数
		 * 2.创建一个抽象工厂参数
		 * 3.利用多态的性质，分别对三个接口参数赋值
		 * 4.调用对象的相应方法即可
		 * 
		 * 而其他的诸如工厂里面到底是什么，工厂提供的两个get方法到底做了些啥，对象的两个方法sayHello和showMy到底做了一些什么
		 * 我们都不用关心，甚至我们完全不知道它的编码实现，但是丝毫不影响我们的实际使用
		 */
	}
}
