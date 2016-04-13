package com.adam.Designs.behaviour.ObserverCase.observer.impl;

import com.adam.Designs.behaviour.ObserverCase.observer.People;

/**
 * 观察者实现类
 */
public class ConPeople implements People {
	
	//定义一个姓名来表示当前观察者
	private String name = null;
	
	public ConPeople(String name){
		this.name = name;
	}

	@Override
	public void showInfo(String info) {
		System.out.println(this.name + "  收到信息：  " + info);
	}

}
