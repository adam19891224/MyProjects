package com.adam.Designs.behaviour.State.main;

import com.adam.Designs.behaviour.State.entity.Entity;
import com.adam.Designs.behaviour.State.state.State;
import com.adam.Designs.behaviour.State.state.impl.ConState1;
import com.adam.Designs.behaviour.State.state.impl.ConState2;

/**
 * 状态模式演示
 * @author adam
 * 2015.6.3
 */
public class Main {

	public static void main(String[] args) {
		//创建一个实体对象
		Entity entity = new Entity();
		
		//创建两个状态
		State state1 = new ConState1("状态1");
		State state2 = new ConState2("状态2");
		
		entity.setState(state1);
		entity.showState();
		
		entity.setState(state2);
		entity.showState();
	}
	
	/*
	 * 总结：
	 * 状态模式就如同上面展示的这样
	 * 我们可以随时改变一个对象的状态，并且可以根据这个对象的不同状态，进行不同的操作等
	 */
}
