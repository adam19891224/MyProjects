package com.adam.Interest.jdk5.Varargs.main;

import com.adam.Interest.jdk5.Varargs.entity.Compute;

public class Main {

	public static void main(String[] args) {
		
		Compute c = new Compute();
		
		int a = c.add(3, 4, 5, 3);
		
		System.out.println(a);
		
	}

}
