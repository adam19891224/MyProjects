package com.adam.Designs.structure.BridgeCase.main;

import com.adam.Designs.structure.BridgeCase.concreteRoad.SpeedWay;
import com.adam.Designs.structure.BridgeCase.concreteRoad.Street;
import com.adam.Designs.structure.BridgeCase.concreteVehicle.Bus;
import com.adam.Designs.structure.BridgeCase.concreteVehicle.Car;

public class Main {

	public static void main(String[] args) {
		//桥接模式演练
		
		//直接定义道路
		SpeedWay sd = new SpeedWay();
		sd.setVehicle(new Car());
		sd.show();
		
		Street s = new Street();
		s.setVehicle(new Bus());
		s.show();
		
	}

	/*
	 * 总结:
	 * 此时我们很轻易的发现，桥接模式的好处就是完全把车和路两个不同的类分离了，我们需要做的仅仅是在创建路的对象时，传递一个车的对象，而且如果我们想往
	 * 车中添加一个展示我当前速度的方法，showMySpeed，也不会影响到路的类
	 */
	
}
