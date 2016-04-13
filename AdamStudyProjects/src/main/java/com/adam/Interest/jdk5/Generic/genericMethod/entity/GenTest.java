package com.adam.Interest.jdk5.Generic.genericMethod.entity;
/**
 * 泛型方法实例
 * @author adam
 * 2015.5.25
 */
public class GenTest {

	//泛型方法使用，同泛型类类似，泛型也可用于方法中，并且声明必须在public或者public static, final等这些关键字之后，返回类型之前
	public static <T> void getTest(T t){
		System.out.println(t);
	}
	
}
