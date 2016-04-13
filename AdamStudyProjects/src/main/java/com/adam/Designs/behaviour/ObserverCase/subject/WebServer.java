package com.adam.Designs.behaviour.ObserverCase.subject;

import com.adam.Designs.behaviour.ObserverCase.observer.People;

/**
 * 主题者，这里模仿的是web服务器
 *
 */
public interface WebServer {

	//内部方法有：注册观察者     注销观察者     发布新信息
	public void register(People people);
	
	public void remove(People people);
	
	public void sendInfo(String info);
	
}
