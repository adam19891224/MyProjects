package com.adam.Interest.jdk5.Varargs.entity;
/**
 * 可变参数使用规则(尽量避免使用)
 * @author adam
 * 2015.5.25
 */
public class Compute {

	/**
	 * 可变参数使用规则：java仅仅只能允许使用一个可变参数，并且可变参数必须放在参数列表的最后一位：
	 */
	public int add(int a, int ... x){//int ... x  就是我们的可变参数,在java中，它是以数组的形式存在的
		if(x.length > 0){
			for(int t : x){
				a += t;
			}
		}
		return a;
	}
	
}
