package com.adam.Designs.behaviour.IteratorCase.iterator.impl;

import com.adam.Designs.behaviour.IteratorCase.aggregate.Aggregate;
import com.adam.Designs.behaviour.IteratorCase.iterator.Loop;

public class Loops implements Loop {
	
	@SuppressWarnings("unused")
	private Aggregate aggregate;
	private Object[] objs;
	private int index;
	private int length;
	
	public Loops(Aggregate aggregate){
		this.aggregate = aggregate;
		this.index = 0;
		this.objs = aggregate.objs();
		this.length = objs.length;
	}

	@Override
	public Object frist() {
		return objs[0];
	}

	@Override
	public Object last() {
		return objs[this.length - 1];
	}

	@Override
	public boolean hasNext() {
		return this.length > this.index;
	}

	@Override
	public Object nextObj() {
		Object o = objs[this.index];
		index ++;
		return o;
	}

}
