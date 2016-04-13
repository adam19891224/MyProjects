package com.adam.Designs.structure.Bridge.implement;

import com.adam.Designs.structure.Bridge.abstracts.MyEntity;

/**
 * 实际的类
 *
 */
public class MyEntityOne extends MyEntity {

	/*
	 * 由于抽象方法的sayHello是抽象方法，所以它的子类必须要实现它
	 * @see com.adam.Designs.structure.Bridge.abstracts.MyEntity#sayHello()
	 */
	@Override
	public void sayHello() {
		System.out.println("你好，我是MyEntity的子类，MyEntityOne");
	}

}
