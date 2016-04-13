package com.adam.Designs.structure.DecortatorCase.decorators;

import com.adam.Designs.structure.DecortatorCase.interfaces.Master;
import com.adam.Designs.structure.DecortatorCase.interfaces.impl.HongQi;

/**
 * 装饰类
 *
 */
public class Decorator implements Master {
	
	public Master master = new HongQi();

	public void fight() {
		master.fight();
		System.out.println("打狗棒法");
	}

}
