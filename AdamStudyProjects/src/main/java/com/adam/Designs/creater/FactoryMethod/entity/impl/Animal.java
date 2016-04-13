package com.adam.Designs.creater.FactoryMethod.entity.impl;

import com.adam.Designs.creater.FactoryMethod.entity.Entity;

/**
 * 一个实现类，实现entity接口，该类表示动物类
 */
public class Animal implements Entity {

	public void say() {
		System.out.println("你好，我是动物实体");
		System.out.println();
	}

}
