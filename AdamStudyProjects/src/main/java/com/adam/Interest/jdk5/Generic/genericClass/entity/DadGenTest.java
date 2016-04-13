package com.adam.Interest.jdk5.Generic.genericClass.entity;
/**
 * 泛型类--父类篇,此时我们还是用SunGenTest的例子，但是我们手动的在comparable后面的泛型加上一个父类限制，看看Main方法又如何使用
 * @author adam
 * 2015.5.25
 */
public class DadGenTest<T, K extends Comparable<? super Double>> {
	
	private T t;
	private K k;
	
	public DadGenTest(T t, K k){
		this.k = k;
		this.t = t;
	}
	
	public K getK(){
		return this.k;
	}
	
	public T getT(){
		return this.t;
	}

}
