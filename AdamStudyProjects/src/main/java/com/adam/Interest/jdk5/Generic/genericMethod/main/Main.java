package com.adam.Interest.jdk5.Generic.genericMethod.main;

import com.adam.Interest.jdk5.Generic.genericMethod.entity.GenTest;

public class Main {

	public static void main(String[] args) {
		
		GenTest.getTest(new Integer(1));
		GenTest.getTest(new GenTest());
	}

}
