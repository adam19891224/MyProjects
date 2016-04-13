package com.adam.Designs.behaviour.Iterator.iterator.impl;

import com.adam.Designs.behaviour.Iterator.aggregate.impl.ConcreteAggregate;
import com.adam.Designs.behaviour.Iterator.iterator.Iterator;

/**
 * 迭代器实现类
 * @author adam
 * 2015.5.27
 */
public class ConcreteIterator implements Iterator {
	
	//定义一个聚合类的声明，并放入构造方法中，表示当创建这个迭代器实现类时，就要同时放入聚合类
	private ConcreteAggregate aggregate = null;
	//同时定义两个标记
	private int size = 0;
	private int index = 0;
	private int suffix = 0;
	
	//构造方法
	public ConcreteIterator(ConcreteAggregate aggregate){
		this.aggregate = aggregate;
		this.size = aggregate.getObjs().length;
	}
	
	
	//然后是几个对应的方法，
	@Override
	public Object frist() {
		return this.aggregate.getObjs()[0];
	}

	@Override
	public Object last() {
		return this.aggregate.getObjs()[size - 1];
	}

	@Override
	public boolean isNotDone() {
		boolean b = this.size > this.suffix;
		this.suffix ++;
		return b;
	}

	@Override
	public Object getCurrent() throws Exception {
		this.index = this.suffix - 1;
		if(this.index < 0){
			throw new Exception("请在循环中使用该方法");
		}
		return this.aggregate.getObjs()[index];
	}
}
