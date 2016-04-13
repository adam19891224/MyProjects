package com.adam.Designs.structure.Adapter.adapter;

public class AbstractEntity extends AbstractInterface {

	//由于此时我们采用继承抽象类的方式来做，所以就不必担心要全部实现接口的方法了
	
	//这里我们只覆盖running方法
	@Override
	public void running() {
		
		System.out.println("小周，加油！");
		
	}
	
	
}
