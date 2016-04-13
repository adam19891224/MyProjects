package com.adam.Designs.structure.BridgeCase.concreteVehicle;

import com.adam.Designs.structure.BridgeCase.abstractsVehicle.Vehicle;

public class Car extends Vehicle {

	@Override
	public void run() {
		System.out.println("我是小轿车");
	}

}
