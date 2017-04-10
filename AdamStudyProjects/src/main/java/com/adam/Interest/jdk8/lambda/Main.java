package com.adam.Interest.jdk8.lambda;

import java.util.List;

import com.adam.Utils.Utils;

/**
 * java 8 lambda表达式初探
 * @author adam
 * 2015.5.21
 */
public class Main {

	public static void main(String[] args) {
		//test1();
		
		//调用下面的add方法
		//System.out.println(add(1, 2));		
		//我们用lambda来看又是怎么做的,由于我们定义了一个lambda接口，我们就用这个接口来做
		addLambda lambda = (int a, int b) -> System.out.println(a + b);
		lambda.add(4, 6);
		//此时我们发现，lambda表达式的写法是我们直接定义一个接口，然后并没有实现它内部的add方法，而是用lambda来赋值，因为此时addLambda内部就只有一个add方法
		//所以我们用addlambda = lambda表达式，程序自然会把这个方法赋值为add的实现方法
		//所以我们后面调用add就能成功,所以这时我们在下面的接口在加一个min方法，java就会报错，说是接口超过了1个方法，lambda不知道赋值给谁
		
		//当然，你会说这里明显add方法更简单，这是因为我们是基于函数来编程的，试想一下，此时我们定义一个add接口，那么我们调用add方法的时候，必然要New:
		//add a = new add{ public void add(){...}  },创建一个接口的实例，则需要用内部类的形式来实现它的内部方法
		//所以用上面的代码来看，lambda显然就简单得多得多了，所以这里我们也可以推敲出，lambda适用于解决内部类等复杂的实现
	}
	
	
	/**
	 * lambda表达式第一章
	 * lambda表达式是java 8的新特性，它主要作用是减少代码的编写，它的格式大体分为两种：
	 * 	1. (parameters) -> experssion
	 *  2. (parameters) -> {statements;}
	 *  
	 * 	上面就是lambda表达式的两种形式，这里先不要问两者的区别，我们下面用代码来慢慢解释
	 */
	public static void test1(){
		/**
		 * 此时我们有一个需求，就是十分简单的遍历一个List集合
		 * 最开始，我们用For循环遍历
		 * 后来，java5引入了增强For循环---foreach
		 * 现在，java8引用了lambda表达式来进行遍历
		 * 话不多说，我们来看下代码
		 */
		List<String> list = Utils.getList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		
		//我们首先用传统的方法来遍历：
		for(int a = 0; a < list.size(); a++){
			System.out.println(list.get(a));
		}
		System.out.println("===================上面是传统for循环===========================");
		/*
		 * 上面发现，传统的方式遍历，我们除了要定义遍历下标a,如果此时我们还需要使用这个对象，那么还需要定义一个string的变量来接收list.get(a);
		 */

		
		//然后是增强for
		for(String s : list){
			System.out.println(s);
		}
		System.out.println("===================上面是增强for循环===========================");
		/*
		 * 此时我们发现，增强for循环就相对而言简单了，它只需要定义一个string的变量即可，并且可以直接对这个s进行操作
		 */
		
		
		//然后我们看一下java 8 的lambda表达式又是如何做的
		list.forEach(s -> System.out.println(s));
		System.out.println("===================上面是lambda表达式===========================");
		/*
		 * 此时我们发现，lambda表达式只用了一行代码，就完成了遍历
		 */
		
		//我们用stream来做这个循环
		list.forEach(System.out::println);
		System.out.println("===================上面是stream功能===========================");
		/*
		 * 我们发现，代码更简单，甚至比lambda都还要简单
		 */
	}
	
	/**
	 * 此时我们定义第二个方法，传入两个整形，然后返回他们的和
	 */
	public static int add(int a, int b){
		return a + b;
	}
	
	
	/**
	 * 此时我们看一看lambda是怎么做的，详见下面的接口,注意，此时这个接口并没有任何实现类，只是一个接口
	 */
	
}

/**
 * 声明一个接口,内部就定义个加法
 */
@FunctionalInterface
interface addLambda{
	void add(int a, int b);
	//void min(int a, int b);
}