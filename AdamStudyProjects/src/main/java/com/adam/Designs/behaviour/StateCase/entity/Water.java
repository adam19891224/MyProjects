package com.adam.Designs.behaviour.StateCase.entity;

import com.adam.Designs.behaviour.StateCase.state.State;

/**
 * 实体类，水
 */
public class Water {

	//创建一个状态标识
	private State state = null;
	
	//添加状态
	public void addState(State s){
		this.state = s; 
	}
	
	//固态执行的方法
	private void solidOper(){
		System.out.println("此时水已结冰，赶紧解冻");
	}
	
	//气态执行的方法
	private void gasOper(){
		System.out.println("此时水已成蒸汽，赶紧用容器罩住降温");
	}
	
	//液态执行的方法
	private void liquidOper(){
		System.out.println("此时水是液态，可以喝了");
	}
	
	//提供给外部的方法方法
	public void operator(){
		if(this.state.showState().equals("gas")){
			this.gasOper();
		}
		if(this.state.showState().equals("solid")){
			this.solidOper();
		}
		if(this.state.showState().equals("liquid")){
			this.liquidOper();
		}
	}
}
