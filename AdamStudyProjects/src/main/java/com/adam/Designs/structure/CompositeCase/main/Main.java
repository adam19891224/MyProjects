package com.adam.Designs.structure.CompositeCase.main;

import com.adam.Designs.structure.CompositeCase.entity.Cats;
import com.adam.Designs.structure.CompositeCase.entity.Tiger;
import com.adam.Designs.structure.CompositeCase.interfaces.Animal;

public class Main {

	public static void main(String[] args) {
		//创建老虎对象
		Tiger t1 = new Tiger();
		Tiger t2 = new Tiger();
		
		t1.showMy();
		
		Animal animal = new Cats();
		animal.getCats().addTiger(t1);
		animal.getCats().addTiger(t2);
		animal.showMy();
		
	}
	
}
