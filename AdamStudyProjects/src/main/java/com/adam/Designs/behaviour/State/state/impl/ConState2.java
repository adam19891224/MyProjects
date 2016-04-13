package com.adam.Designs.behaviour.State.state.impl;

import com.adam.Designs.behaviour.State.state.State;

/**
 * 状态实体2
 *
 */
public class ConState2 implements State {
	
	private String state = null;
	
	public ConState2(String s){
		this.state = s;
	}

	@Override
	public void show() {
		System.out.println("状态：   " + this.state);
	}

}
