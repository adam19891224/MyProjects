package com.adam.Designs.behaviour.Observer.subject.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.adam.Designs.behaviour.Observer.observer.Observer;
import com.adam.Designs.behaviour.Observer.subject.Subject;

/**
 * 主题实际对象
 */
public class ConSubject implements Subject {
	
	//定义一个set来存储所有的元素
	private final Set<Observer> set = new HashSet<Observer>();

	@Override
	public void addOb(Observer observer) {
		if(this.set.contains(observer)){
			System.out.println("对不起，您已经订阅了");
		}else{
			this.set.add(observer);
		}
	}

	@Override
	public void removeOb(Observer observer) {
		this.set.remove(observer);
	}

	@Override
	public void update(String s) {
		//让set中的所有元素得到更新信息,由于set没有下标且无序，所以没办法用get来随机访问，这里就用迭代器来遍历
		Iterator<Observer> iterator = this.set.iterator();
		while(iterator.hasNext()){
			iterator.next().getInfo(s);
		}
	}

}
