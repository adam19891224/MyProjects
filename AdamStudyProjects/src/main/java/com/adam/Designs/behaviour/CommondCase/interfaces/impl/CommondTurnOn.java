package com.adam.Designs.behaviour.CommondCase.interfaces.impl;

import com.adam.Designs.behaviour.CommondCase.interfaces.Commond;
import com.adam.Designs.behaviour.CommondCase.reciver.Television;

/**
 * 开机命令
 *
 */
public class CommondTurnOn implements Commond {

	private Television television;
	
	public CommondTurnOn(Television t){
		this.television = t;
	}

	@Override
	public void operatorTV() {
		//由于这是开机命令，就执行开机方法
		this.television.turnOn();
	}
	
}
