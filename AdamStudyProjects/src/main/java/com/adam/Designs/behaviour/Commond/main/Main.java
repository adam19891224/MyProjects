package com.adam.Designs.behaviour.Commond.main;

import com.adam.Designs.behaviour.Commond.interfaces.Commond;
import com.adam.Designs.behaviour.Commond.interfaces.impl.ConcreteCommond;
import com.adam.Designs.behaviour.Commond.reciver.Reciver;
import com.adam.Designs.behaviour.Commond.transmit.People;

/**
 * 命令模式演示
 *
 */
public class Main {

	public static void main(String[] args) {
		//创建命令执行对象，命令对象，命令发起者对象
		Reciver reciver = new Reciver();
		Commond commond = new ConcreteCommond(reciver);
		People people = new People(commond);
		
		//直接调用发起请求者的执行命令方法
		people.actionCommond();
		
	}
	
	/*
	 * 总结：
	 * 我们可以发现，这种方式完美的运行完毕了，同时我们也要注意的是，这种方法的好处是让发起请求者与命令实际执行者直接实现了松耦合，即通过一个中间件命令对象来管理
	 * 如果我们需要增加其他命令，则创建另一个命令对象即可，这样的话，我们就只需要针对命令来做编程，而不用管实际处理的执行。大大增加了代码的健壮性
	 */
}
