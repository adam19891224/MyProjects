package com.adam.Designs.behaviour.IteratorCase.aggregate;

import com.adam.Designs.behaviour.IteratorCase.iterator.Loop;

/**
 * 迭代器模式作业
 * @author adam
 * 2015.5.27
 */
public interface Aggregate {
	
	public Loop loop();
	
	public Object[] objs();
	
}
