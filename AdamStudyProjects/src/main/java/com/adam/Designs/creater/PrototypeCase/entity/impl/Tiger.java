package com.adam.Designs.creater.PrototypeCase.entity.impl;

import com.adam.Designs.creater.PrototypeCase.entity.Animal;

/**
 * animal实现类---老虎类
 *
 */
public class Tiger implements Animal {

	public Object clone(){
		return new Tiger();
	}
	
	
	//重写toString
	@Override
	public String toString() {
		return "你好，我是老虎";
	}
	
}
