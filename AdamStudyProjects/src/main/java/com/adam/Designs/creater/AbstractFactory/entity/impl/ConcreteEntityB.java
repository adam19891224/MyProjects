package com.adam.Designs.creater.AbstractFactory.entity.impl;

import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityB;

/**
 * 抽象类B的实现类
 */
public class ConcreteEntityB implements AbstractEntityB {

	public void showMy() {
		System.out.println("你好，我是B对象   ~~~");
		System.out.println();
	}

}
