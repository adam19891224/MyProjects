package com.adam.Designs.behaviour.Observer.subject;

import com.adam.Designs.behaviour.Observer.observer.Observer;

/**
 * 观察者模式
 * 观察者模式可以说是最常用的模式之一，它的大致思想如下：
 * 通过一个主题者，来管理所有的观察者，主题者内部有一个容器用于存放观察者，当观察者需要查看这个主题时，调用主题的注册方法，然后主题每次有更新时，就调用更新方法，同时告诉所有
 * 自己的观察者，如果此时观察者不想继续关注主题了，就调用主题的撤销方法，把自己从主题者的名单中移除即可
 * 
 * 在现实生活中，就有许多这种例子，例如订阅邮件，新闻，服务器推送信息给所有的移动客户等。
 * @author adam
 * 2015.6.2
 */
public interface Subject {

	//这就是主题者，内部三个方法，一是注册观察者，二是注销观察者，三是发布更新
	
	//注册
	public void addOb(Observer observer);
	
	//撤销
	public void removeOb(Observer observer);
	
	//发布更新
	public void update(String s);
	
}
