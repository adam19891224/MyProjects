package com.adam.Interest.jdk5.Generic.genericClass.entity;
/**
 * 泛型类
 * @author adam
 * 2015.5.25
 */
public class GenTest<T> {

	private T t;
	
	public GenTest(T t){
		this.t = t;
	}
	
	public T getT(){
		return this.t;
	}
}
