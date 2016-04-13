package com.adam.Designs.behaviour.State.entity;

import com.adam.Designs.behaviour.State.state.State;

/**
 * 实体类
 *
 */
public class Entity {

	//内部定义一个状态接口
	private State state = null;
	
	//设置状态的方法
	public void setState(State state){
		this.state = state;
	}
	
	//展示状态的方法
	public void showState(){
		//调用当前状态的show方法
		this.state.show();
	}
	
}
