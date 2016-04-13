package com.adam.Designs.creater.FactoryMethod.entity.impl;

import com.adam.Designs.creater.FactoryMethod.entity.Entity;

/**
 * 一个实现类，实现Entity接口，该类表示植物实体
 */
public class Plant implements Entity {

	public void say() {
		System.out.println("你好，我是植物实体");
		System.out.println();
	}

}
