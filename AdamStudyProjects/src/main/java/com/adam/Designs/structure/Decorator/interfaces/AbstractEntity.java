package com.adam.Designs.structure.Decorator.interfaces;
/**
 * 装饰模式
 * 1.该模式目的是在现有类的基础上进行扩展操作，同时不会对现有类造成影响
 * 2.该模式并不是唯一解决上面问题的模式，但是针对传统的例如创建子类来说，该模式会更简单
 * @author Administrator
 * 2015.5.12
 */
public interface AbstractEntity {

	//一个简单的方法
	public void sayHello();
	
}
