package com.adam.Designs.behaviour.StateCase.main;

import com.adam.Designs.behaviour.StateCase.entity.Water;
import com.adam.Designs.behaviour.StateCase.state.impl.Gas;
import com.adam.Designs.behaviour.StateCase.state.impl.Liquid;

/**
 * 状态模式作业
 * @author adam
 * 2015.6.3
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//创建水实体
		Water water = new Water();
		
		//此时假设水是气态
		water.addState(new Gas());
		water.operator();
		
		//修改为液态
		water.addState(new Liquid());
		water.operator();
	}
	
}
