package com.adam.Designs.creater.Singleton.main;

import com.adam.Designs.creater.Singleton.entity.HungryMan;
import com.adam.Designs.creater.Singleton.entity.LazyMan;

/**
 * 单例模式调用模拟
 */
public class Main {

	public static void main(String[] args) {
		//创建两个对象
//		LazyMan lman = new LazyMan();//由于我们的构造函数变成了私有的，所以此时直接New就会报错
		LazyMan lman = LazyMan.getInstence();
		System.out.println("lazyman的结果是：" + lman);
		
		HungryMan hman = HungryMan.getInstence();
		System.out.println("hungryman的结果是：" + hman);
		
	}
	
}
