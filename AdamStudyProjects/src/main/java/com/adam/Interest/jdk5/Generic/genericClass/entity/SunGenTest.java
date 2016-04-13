package com.adam.Interest.jdk5.Generic.genericClass.entity;
/**
 * 泛型类-子类篇
 * 
 * 此时我们让这个类传递两个泛型，一个是T，一个必须是Comparable的子类，看看main方法中的使用规则
 * @author adam
 * 2015.5.25
 */
public class SunGenTest<T, S extends Comparable<S>> {

	private T t;
	private S s;
	
	public SunGenTest(T t, S s){
		this.t = t;
		this.s = s;
	}

	public T getT(){
		return this.t;
	}
	
	public S getS(){
		return this.s;
	}
	
}
