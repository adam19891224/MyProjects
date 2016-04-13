package com.adam.Designs.creater.Prototype.entity.impl;

import com.adam.Designs.creater.Prototype.entity.Prototype;

/**
 * 原型实现类1
 *
 */
public class ConcreteP1 implements Prototype {

	public Object clone(){
		return new ConcreteP1();
	}
}
