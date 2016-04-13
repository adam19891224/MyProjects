package com.adam.Interest.jdk5.Comparable.main;

import java.util.Collections;
import java.util.List;

import com.adam.Interest.jdk5.Comparable.entity.A;
import com.adam.Utils.Utils;

public class Main {

	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new A();
		A a3 = new A();
		A a4 = new A();
		A a5 = new A();
		
		a1.setAge(3);
		a1.setName("aaa");
		a1.setNum(1);
		
		a2.setAge(2);
		a2.setName("bbb");
		a2.setNum(2);
		
		a3.setAge(1);
		a3.setName("ccc");
		a3.setNum(2);
		
		a4.setAge(4);
		a4.setName("ddd");
		a4.setNum(99);
		
		a5.setAge(4);
		a5.setName("eee");
		a5.setNum(4);
		
		List<A> list = Utils.getList();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		list.add(a5);
		
		Collections.sort(list);
		
		for(A a : list){
			System.out.println("age:" + a.getAge() + "  num:" + a.getNum() + "   name:" + a.getName());
		}
	}
	
}
