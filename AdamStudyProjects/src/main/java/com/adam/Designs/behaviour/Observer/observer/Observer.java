package com.adam.Designs.behaviour.Observer.observer;
/**
 * 观察者对象，实际就是主题的接受者，也就是广大客户
 *
 */
public interface Observer {

	//内部就一个方法，接收主题的信息
	public void getInfo(String s);
	
}
