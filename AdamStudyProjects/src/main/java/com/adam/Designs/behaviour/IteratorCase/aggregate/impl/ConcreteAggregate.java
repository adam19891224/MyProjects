package com.adam.Designs.behaviour.IteratorCase.aggregate.impl;

import com.adam.Designs.behaviour.IteratorCase.aggregate.Aggregate;
import com.adam.Designs.behaviour.IteratorCase.iterator.Loop;
import com.adam.Designs.behaviour.IteratorCase.iterator.impl.Loops;

/**
 * 迭代器模式作业---聚合对象
 *
 */
public class ConcreteAggregate implements Aggregate {
	
	private Object[] objs = null;

	@Override
	public Loop loop() {
		return new Loops(this);
	}

	@Override
	public Object[] objs() {
		return this.objs;
	}

	public ConcreteAggregate(Object[] obj){
		this.objs = obj;
	}
	
}
