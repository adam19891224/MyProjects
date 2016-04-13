package com.adam.Designs.behaviour.Iterator.aggregate.impl;

import com.adam.Designs.behaviour.Iterator.aggregate.Aggregate;
import com.adam.Designs.behaviour.Iterator.iterator.Iterator;
import com.adam.Designs.behaviour.Iterator.iterator.impl.ConcreteIterator;

/**
 * 集合接口实现类
 * @author adam
 * 2015.5.27
 *
 */
public class ConcreteAggregate implements Aggregate {

	//定义一个对象数组
	private Object[] objs = null;
	
	@Override
	public Iterator getIterator() {
		return new ConcreteIterator(this);
	}

	public ConcreteAggregate(Object[] objs){
		this.objs = objs;
	}
	
	//只提供get方法
	public Object[] getObjs() {
		return objs;
	}
	
}
