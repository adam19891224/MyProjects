package com.adam.Designs.behaviour.ObserverCase.subject.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.adam.Designs.behaviour.ObserverCase.observer.People;
import com.adam.Designs.behaviour.ObserverCase.subject.WebServer;

/**
 * 主题实现类，这里模仿新浪服务器
 *
 */
public class Sina implements WebServer {

	//定义一个存放观察者的集合
	private final Set<People> set = new HashSet<People>();
	
	@Override
	public void register(People people) {
		if(this.set.contains(people)){
			throw new IllegalArgumentException("用户已经存在");
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
		//遍历当前的用户集合，发布信息
		Iterator<People> iterator = this.set.iterator();
		while(iterator.hasNext()){
			iterator.next().showInfo(info);
		}
	}

}
