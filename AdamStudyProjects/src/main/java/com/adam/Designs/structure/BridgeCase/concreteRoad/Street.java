package com.adam.Designs.structure.BridgeCase.concreteRoad;

import com.adam.Designs.structure.BridgeCase.abstractsRoad.Road;

public class Street extends Road {

	@Override
	public void show() {
		getVehicle().run();
		System.out.println("我正在市中心上行驶");
	}

}
