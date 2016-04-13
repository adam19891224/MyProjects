package com.adam.Designs.behaviour.CommondCase.main;

import com.adam.Designs.behaviour.CommondCase.transmit.ControlMachine;

/**
 * 命令模式作业展示
 * @author adam
 * 2015.5.22
 */
public class Main {

	public static void main(String[] args) {
		//创建遥控器
		ControlMachine machine = new ControlMachine();
		
		//执行开机，关机和换台
		machine.turnOn();

		machine.channelChange(4);
		
		machine.turnOff();
	}
	
}
