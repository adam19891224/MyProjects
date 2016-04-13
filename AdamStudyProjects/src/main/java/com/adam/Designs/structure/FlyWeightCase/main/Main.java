package com.adam.Designs.structure.FlyWeightCase.main;

import com.adam.Designs.structure.FlyWeightCase.entity.Entity;
import com.adam.Designs.structure.FlyWeightCase.factory.Factory;

public class Main {

	public static void main(String[] args) {
		
		Entity e1 = new Entity("a");
		Entity e2 = new Entity("b");
		Entity e3 = new Entity("c");
		Entity e4 = new Entity("a");
		
		Factory factory = new Factory();
		factory.getObj(e1);
		factory.getObj(e2);
		factory.getObj(e3);
		factory.getObj(e4);
		
		factory.checkMap();
	}
	
}
