package com.adam.Designs.creater.PrototypeCase.entity.impl;

import com.adam.Designs.creater.PrototypeCase.entity.Animal;

public class Dog implements Animal {
	
	public Object clone(){
		return new Dog();
	}
	
	public static class Builder{
		
		
		
	}
	
	//重写toString
	@Override
	public String toString() {
		return "你好，我是小狗";
	}
}
