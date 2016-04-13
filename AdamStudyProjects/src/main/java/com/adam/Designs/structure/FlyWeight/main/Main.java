package com.adam.Designs.structure.FlyWeight.main;

import java.util.Map;

import com.adam.Designs.structure.FlyWeight.factory.FlyWeightFactory;
import com.adam.Utils.Utils;

/**
 * 享元模式的main方法
 * @author adam
 * 2015.5.14
 */
public class Main {

	public static void main(String[] args) {
		//创建工厂和map
		Map<String, Object> map = Utils.getMap();
		
		FlyWeightFactory factory = new FlyWeightFactory.Builder().map(map).build();
		
		//向factory存值
		factory.getWord("a");
		factory.getWord("b");
		factory.getWord("c");
		factory.getWord("d");
		factory.getWord("a");
		
		//然后采用checkmap方法
		factory.checkMap();
	}
	
	/*
	 * 总结
	 * 我们打印发现，虽然我们存入了5个值，但是实际上却只打印了4个，这就是享元模式的简单应用，它会把我们常常使用的对象放入内存，这样让我们后面再次使用这个对象时
	 * 就会从map中去取，从而减少了内存的开销
	 */

}
