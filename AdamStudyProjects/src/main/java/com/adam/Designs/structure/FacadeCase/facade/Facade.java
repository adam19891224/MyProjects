package com.adam.Designs.structure.FacadeCase.facade;

import com.adam.Designs.structure.FacadeCase.entity.Room;
import com.adam.Designs.structure.FacadeCase.entity.Saloon;

public class Facade {
	
	private Saloon saloon = new Saloon();
	
	private Room room = new Room();
	
	public void onLight(){
		this.saloon.onLight();
		this.room.onLight();
	}

}
