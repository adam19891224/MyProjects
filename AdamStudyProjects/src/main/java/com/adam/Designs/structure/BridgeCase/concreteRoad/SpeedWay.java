package com.adam.Designs.structure.BridgeCase.concreteRoad;

import com.adam.Designs.structure.BridgeCase.abstractsRoad.Road;

public class SpeedWay extends Road {

	@Override
	public void show() {
		getVehicle().run();
		System.out.println("我正在高速公路上行驶");
	}

}
