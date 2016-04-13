package com.adam.Designs.behaviour.ObserverCase.main;

import com.adam.Designs.behaviour.ObserverCase.observer.People;
import com.adam.Designs.behaviour.ObserverCase.observer.impl.ConPeople;
import com.adam.Designs.behaviour.ObserverCase.subject.WebServer;
import com.adam.Designs.behaviour.ObserverCase.subject.impl.Sina;
import com.adam.Designs.behaviour.ObserverCase.subject.impl.Sohu;

public class Main {

	public static void main(String[] args) {
		
		//创建三个用户
		People zhang = new ConPeople("张三");
		People wang = new ConPeople("王五");
		People li = new ConPeople("李四");
		
		//创建服务器
		WebServer sina = new Sina();
		WebServer sohu = new Sohu();
		
		//让所有三个用户都订阅新浪
		sina.register(zhang);
		sina.register(wang);
		sina.register(li);
		sina.sendInfo("欢迎订阅新浪服务");
		
		//让张三李四订阅搜狐
		sohu.register(zhang);
		sohu.register(li);
		sohu.sendInfo("欢迎订阅搜狐服务");
		
		System.out.println("=============================张三退出订阅新浪============================");
		sina.remove(zhang);
		sina.sendInfo("新浪服务用户更新");
		
	}
	
}
