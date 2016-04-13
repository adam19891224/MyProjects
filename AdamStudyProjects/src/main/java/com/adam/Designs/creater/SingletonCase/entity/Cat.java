package com.adam.Designs.creater.SingletonCase.entity;

public class Cat {

	//私有构造
	private Cat(){}
	
	//定义一个静态变量
	private static Cat cat = null;
	
	public static final Cat newInstence(){
		//为了严格保证只有一个对象，所以要先判断
		if(cat == null){
			cat = new Cat();
		}
		return cat;
	}
	
	//我们发现，这就是懒汉单例模式
	
}
