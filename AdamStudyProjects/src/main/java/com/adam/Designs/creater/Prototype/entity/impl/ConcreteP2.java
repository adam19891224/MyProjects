package com.adam.Designs.creater.Prototype.entity.impl;

import com.adam.Designs.creater.Prototype.entity.Prototype;

/**
 * 实现类2
 *
 */
public class ConcreteP2 implements Prototype {

	public Object clone(){
		return new ConcreteP2();
	}
	
}
