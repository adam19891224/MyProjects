package com.adam.Designs.behaviour.State.state.impl;

import com.adam.Designs.behaviour.State.state.State;

/**
 * 状态实体1
 *
 */
public class ConState1 implements State {

	//定义一个状态标识
	private String state = null;
	
	//通过构造方法来创建这个标识
	public ConState1(String state){
		this.state = state;
	}
	
	@Override
	public void show() {
		System.out.println("状态是：   " + this.state);
	}

}
