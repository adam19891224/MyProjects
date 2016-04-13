package com.adam.Designs.behaviour.CommondCase.interfaces.impl;

import com.adam.Designs.behaviour.CommondCase.interfaces.Commond;
import com.adam.Designs.behaviour.CommondCase.reciver.Television;

/**
 * 关机命令
 *
 */
public class CommondTurnOff implements Commond {
	
	private Television television;
	
	public CommondTurnOff(Television t){
		this.television = t;
	}

	@Override
	public void operatorTV() {
		//执行关机方法
		this.television.turnOff();
	}

}
