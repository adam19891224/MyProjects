package com.adam.Interest.javaknowledge.Enum.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.adam.Interest.javaknowledge.Enum.enums.Color;

/**
 * 枚举用法
 */
public class Main {

	public static void main(String[] args) {
		Color c = Color.BLUE;
		System.out.println(c);
		System.out.println(c.add(1, 2));
		
		String[] s = new String[10];
		s[0] = "1";
		s[1] = "2";
		Set<String> set = new HashSet<String>(Arrays.asList(s));
		String[] s2 = set.toArray(new String[set.size()]);
		System.out.println(Arrays.toString(s2));
	}
	
	/*
	 * 总结
	 * 我们发现，枚举类型就像是一个普通类一样，只是它在定义的时候，就相当于创建了
	 * 所以为什么我们在写枚举的构造函数时，如果带参数，则原来定义的变量必须同时带上参数
	 * 而使用也和对象一致，类  对象 = 类.对象， 和静态类使用是一样的，因为它创建一个对象，不需要new关键字
	 * 
	 * 那么有人就会问了，枚举的作用是什么，什么时候用呢？
	 * 我们上面提到，枚举就和静态类相似，而往往静态类又是我们用来定义常量的方式，所以我们以前定义的常量：public static final ......
	 * 现在就可以完全用枚举类来代替了
	 */
	
}
