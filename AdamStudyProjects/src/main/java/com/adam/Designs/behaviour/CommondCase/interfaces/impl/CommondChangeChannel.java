package com.adam.Designs.behaviour.CommondCase.interfaces.impl;

import com.adam.Designs.behaviour.CommondCase.interfaces.Commond;
import com.adam.Designs.behaviour.CommondCase.reciver.Television;

/**
 * 换台命令
 *
 */
public class CommondChangeChannel implements Commond {
	
	private Television television;
	
	//定义个切换台的数字
	private int channelNum;
	
	public CommondChangeChannel(Television t, int channelNum){
		this.television = t;
		this.channelNum = channelNum;
	}

	@Override
	public void operatorTV() {
		//执行换台操作
		this.television.changeChannel(channelNum);
	}

	
	
}
