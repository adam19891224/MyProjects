package com.adam.Designs.behaviour.Commond.transmit;

import com.adam.Designs.behaviour.Commond.interfaces.Commond;

/**
 * 定义一个people来充当请求发起者
 *
 */
public class People {

	//它内部包含两个方法，一个是创建命令的方法，一个是执行命令的方法，同样，创建命令就用构造方法的形式
	private Commond commond;
	
	public People(Commond c){
		this.commond = c;
	}
	
	//执行命令的方法
	public void actionCommond(){
		this.commond.excute();
	}
	
}
