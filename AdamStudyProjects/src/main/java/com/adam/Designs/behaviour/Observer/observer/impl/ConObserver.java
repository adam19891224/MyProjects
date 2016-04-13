package com.adam.Designs.behaviour.Observer.observer.impl;

import com.adam.Designs.behaviour.Observer.observer.Observer;
/**
 * 观察者实现类，就相当于我们的客户端
 *
 */
public class ConObserver implements Observer {

	@Override
	public void getInfo(String s) {
		System.out.println("客户端接到更新信息： " + s);
	}
}
