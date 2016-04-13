package com.adam.Designs.creater.SingletonCase.entity;

public class Dog {
	
	//私有构造，让外界不能自己new对象
	private Dog(){}
	
	//定义一个静态变量，并创建它
	private static final Dog dog = new Dog();
	
	public static final Dog newInstence(){
		return dog;
	}
	
	//我们可以看出，这是饿汉单例模式

}
