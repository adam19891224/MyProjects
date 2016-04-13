package com.adam.Designs.creater.PrototypeCase.manager;

import java.util.HashMap;
import java.util.Map;

import com.adam.Designs.creater.PrototypeCase.entity.Animal;

public class AnimalManager {
	
	private static final Map<String, Animal> map = new HashMap<String, Animal>();
	
	public void setAnimal(String name, Animal animal){
		map.put(name, animal);
	}
	
	public Animal getAnimal(String name) throws Exception{
		Animal animal = map.get(name);
		if(animal == null){
			throw new Exception("不好意思，没有与该名字对应的动物对象");
		}
		return animal;
	}
	
	public void remove(String name){
		map.remove(name);
	}

}
