package com.adam.Designs.behaviour.ObserverCase.subject.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.adam.Designs.behaviour.ObserverCase.observer.People;
import com.adam.Designs.behaviour.ObserverCase.subject.WebServer;

/**
 * 主题实现类，这里模仿搜狐
 *
 */
public class Sohu implements WebServer {
	
	private final Set<People> set = new HashSet<People>();

	@Override
	public void register(People people) {
		if(this.set.contains(people)){
			throw new IllegalArgumentException("用户已存在");
		}else{
			this.set.add(people);
		}
	}

	@Override
	public void remove(People people) {
		if(this.set.contains(people)){
			this.set.remove(people);
		}
	}

	@Override
	public void sendInfo(String info) {
		Iterator<People> iterator = this.set.iterator();
		while(iterator.hasNext()){
			iterator.next().showInfo(info);
		}
	}

}
