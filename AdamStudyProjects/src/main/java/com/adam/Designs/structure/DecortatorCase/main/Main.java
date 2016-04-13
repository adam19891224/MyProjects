package com.adam.Designs.structure.DecortatorCase.main;

import com.adam.Designs.structure.DecortatorCase.decorators.Decorator;
import com.adam.Designs.structure.DecortatorCase.interfaces.Master;
import com.adam.Designs.structure.DecortatorCase.interfaces.impl.HongQi;

/**
 * 装饰者模式作业
 *
 */
public class Main {

	public static void main(String[] args) {
		//创建一个师傅对象
		Master hongqi = new HongQi();
		hongqi.fight();
		
		//创建一个装饰者对象，即我们的徒弟对象，这里就以郭靖为准
		Decorator guojing = new Decorator();
		guojing.fight();
	}
	
}
