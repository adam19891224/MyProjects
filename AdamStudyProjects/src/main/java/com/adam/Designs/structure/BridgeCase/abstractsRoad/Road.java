package com.adam.Designs.structure.BridgeCase.abstractsRoad;


import com.adam.Designs.structure.BridgeCase.abstractsVehicle.Vehicle;

/**
 * 抽象公路类
 * @author adam
 *
 */
public abstract class Road {

	public Vehicle vehicle;
	
	public abstract void show();

	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
	
}
