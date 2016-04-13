package com.adam.Designs.behaviour.Observer.main;

import com.adam.Designs.behaviour.Observer.observer.impl.ConObserver;
import com.adam.Designs.behaviour.Observer.subject.impl.ConSubject;

/**
 * 观察者模式演示
 * @author adam
 * 2015.6.2
 */
public class Main {

	public static void main(String[] args) {
		
		//创建一个主题者和几个观察者
		ConSubject subject = new ConSubject();
		
		ConObserver observer1 = new ConObserver();
		ConObserver observer2 = new ConObserver();
		ConObserver observer3 = new ConObserver();
		ConObserver observer4 = new ConObserver();
		
		subject.addOb(observer1);
		subject.addOb(observer2);
		subject.addOb(observer3);
		subject.addOb(observer4);
		subject.addOb(observer1);
		
		//发布更新
		subject.update("大家好，谢谢大家的订阅");
		
		//让obser3注销订阅
		subject.removeOb(observer3);
		
		//发布更新
		subject.update("obs3已经退出了订阅");
	}
	
	/*
	 * 总结：
	 * 观察者模式就是这样，它的用处十分巨大，例子我们就不一一列举了，它适用的场景也是五花八门，反正当我们需要更新某一个方面，同时顺带更新其他的方面时，就可以采用这种模式
	 * 当然，还有当一个对象发生改变之后，我们并不知道还有多少对象需要联动改变，那么就可以用这种模式，因为此模式我们也没有关心到底有多少观察者被注册了，我们只关心的是当我们
	 * 有更新信息时，保证所有观察者都能得到通知即可
	 * 
	 */
	
}
