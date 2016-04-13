package com.adam.Designs.structure.Bridge.implement;

import com.adam.Designs.structure.Bridge.abstracts.AbstractEntity;
import com.adam.Designs.structure.Bridge.abstracts.MyEntity;

/**
 * 抽象类的实现类
 *
 */
public class AbstractEntityObj extends AbstractEntity {

	@Override
	public void showMyName(MyEntity e) {
		//调用e的方法
		e.sayHello();
		System.out.println("你好，我是抽象类AbstractEntity的子类，AbstractEntityObj");
	}

}
