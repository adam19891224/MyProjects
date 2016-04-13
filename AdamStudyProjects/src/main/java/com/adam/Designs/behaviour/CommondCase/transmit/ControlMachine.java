package com.adam.Designs.behaviour.CommondCase.transmit;

import com.adam.Designs.behaviour.CommondCase.interfaces.impl.CommondChangeChannel;
import com.adam.Designs.behaviour.CommondCase.interfaces.impl.CommondTurnOff;
import com.adam.Designs.behaviour.CommondCase.interfaces.impl.CommondTurnOn;
import com.adam.Designs.behaviour.CommondCase.reciver.Television;

/**
 * 命令执行者，即遥控器
 *
 */
public class ControlMachine {

	//创建电视
	public Television television = new Television();
	
	//执行开机
	public void turnOn(){
		CommondTurnOn turnOn = new CommondTurnOn(television);
		turnOn.operatorTV();
	}
	
	//关机
	public void turnOff(){
		CommondTurnOff turnOff = new CommondTurnOff(television);
		turnOff.operatorTV();
	}
	
	//换台
	public void channelChange(int num){
		CommondChangeChannel channel = new CommondChangeChannel(television, num);
		channel.operatorTV();
	}
	
}
