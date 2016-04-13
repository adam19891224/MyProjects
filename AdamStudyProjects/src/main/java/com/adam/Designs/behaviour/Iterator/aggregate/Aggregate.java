package com.adam.Designs.behaviour.Iterator.aggregate;

import com.adam.Designs.behaviour.Iterator.iterator.Iterator;

/**
 * 迭代器模式的聚合类接口
 *
 */
public interface Aggregate {

	//内部就一个方法，返回这个接口实现类的迭代器对象
	public Iterator getIterator();
	
}
