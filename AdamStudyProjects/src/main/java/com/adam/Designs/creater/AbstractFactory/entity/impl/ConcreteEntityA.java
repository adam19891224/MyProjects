package com.adam.Designs.creater.AbstractFactory.entity.impl;

import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityA;

/**
 * 抽象类A的具体实现类
 */
public class ConcreteEntityA implements AbstractEntityA {

	public void sayHello() {
		System.out.println("你好！ 我是A对象   ~~~");
		System.out.println();
	}

}
