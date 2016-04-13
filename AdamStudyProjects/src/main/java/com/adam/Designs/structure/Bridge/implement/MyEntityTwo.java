package com.adam.Designs.structure.Bridge.implement;

import com.adam.Designs.structure.Bridge.abstracts.MyEntity;

/**
 * 第二个实际类
 *
 */
public class MyEntityTwo extends MyEntity {

	@Override
	public void sayHello() {
		System.out.println("你好，我是MyEntity的子类，MyEntityTwo");
	}

}
