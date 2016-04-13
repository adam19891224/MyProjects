package com.adam.Designs.structure.FlyWeightCase.entity;
/**
 * 享元模式作业
 * @author adam
 * 2015.5.19
 */
public class Entity {

	private String str = null;
	
	public Entity(String s){
		this.str = s;
	}
	
	public String getS(){
		return this.str;
	}
	
}
