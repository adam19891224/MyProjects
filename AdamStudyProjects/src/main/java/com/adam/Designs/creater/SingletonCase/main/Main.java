package com.adam.Designs.creater.SingletonCase.main;

import com.adam.Designs.creater.SingletonCase.entity.Cat;
import com.adam.Designs.creater.SingletonCase.entity.Dog;

public class Main {

	public static void main(String[] args) {
		
		//验证，定义四个变量，两个狗，两个猫
		Dog dog1 = null;
		Dog dog2 = null;
		
		Cat cat1 = null;
		Cat cat2 = null;
		
		dog1 = Dog.newInstence();
		dog2 = Dog.newInstence();
		
		System.out.println(dog1 + "   与       " + dog2);
		System.out.println("他们的判断");
		System.out.println("==判断：" + (dog1 == dog2));
		System.out.println("equal判断: " + (dog1.equals(dog2)));
		
		cat1 = Cat.newInstence();
		cat2 = Cat.newInstence();
		
		System.out.println(cat1 + "   与       " + cat2);
		System.out.println("他们的判断");
		System.out.println("==判断：" + (cat1 == cat2));
		System.out.println("equal判断: " + (cat1.equals(cat2)));
		
		/*
		 * 总结
		 * 经过上面的打印，我们很直观的发现，单例模式下，无论创建多少个对象，都是指向的同一对象实体
		 * 它们的==或者equal判断都是一样的，更能证明这一点
		 */
		
	}
	
}
