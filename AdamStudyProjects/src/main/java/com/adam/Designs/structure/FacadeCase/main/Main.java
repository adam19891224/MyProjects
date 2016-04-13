package com.adam.Designs.structure.FacadeCase.main;

import com.adam.Designs.structure.FacadeCase.facade.Facade;


public class Main {

	public static void main(String[] args) {
		//创建总控制开关对象，即我们的外观对象
		Facade facade = new Facade();
		
		//调用开灯
		facade.onLight();
	}
	
}
