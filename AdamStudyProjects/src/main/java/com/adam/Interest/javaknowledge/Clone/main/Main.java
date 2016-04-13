package com.adam.Interest.javaknowledge.Clone.main;

import com.adam.Interest.javaknowledge.Clone.entity.A;


public class Main {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		A a1 = new A();
		A a2 = new A();
		a1.setName("aaa");
		a2 = (A) a1.clone();
		a2.setName("bbb");
		
		System.out.println(a1.getName());
		System.out.println(a2.getName());
		
	}

}
