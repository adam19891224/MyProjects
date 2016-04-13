package com.adam.Interest.javaknowledge.Clone.entity;

public class A implements Cloneable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		A a = null;
		try {
			a = (A) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
		
	}
	
	
}
