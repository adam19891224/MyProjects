package com.adam.Designs.behaviour.CommondCase.reciver;
/**
 * 命令模式作业
 * 这里我们模拟电视的命令，这就是电视类，里面有三个方法，开机，关机，换台
 * 看看用命令模式怎么来操作这台电视
 * @author adam
 * 2015.5.22
 *
 */
public class Television {

	public void turnOn(){
		System.out.println("电视机打开。。。。");
	}
	
	public void turnOff(){
		System.out.println("电视机关闭。。。。");
	}
	
	private int channel;
	
	public void changeChannel(int num){
		this.channel = num;
		System.out.println("已切换到   " + this.channel + " 台");
	}
	
}
