package com.adam.Designs.behaviour.Iterator.main;

import com.adam.Designs.behaviour.Iterator.aggregate.Aggregate;
import com.adam.Designs.behaviour.Iterator.aggregate.impl.ConcreteAggregate;
import com.adam.Designs.behaviour.Iterator.iterator.Iterator;

/**
 * 迭代器模式演示
 * @author adam
 * 2015.5.27
 */
public class Main {

	public static void main(String[] args) {
		//首先创建一个Int数组,当然，采用多态的父类来声明
		Object[] ints = {1, 2, 3, 4, 5, 6, 7}; 
		/**
		 * 这里就不能用int[] 来声明了，因为Int是Object的子类，如果用int，则局和对象无法传参（聚合对象是Object的数组声明）
		 * 但是用object[]来声明，就能传进聚合对象了，并且由于多态，此时父类object对应的就是子类的int数组
		 */
		//创建聚合对象,同样用多态
		Aggregate aggregate = new ConcreteAggregate(ints);
		//得到聚合对象的迭代器
		Iterator iterator = aggregate.getIterator();
		//尝试打印iterator的第一个和最后一个
		System.out.println("第一个是:   " + iterator.frist().toString() + "  最后一个是:   " + iterator.last().toString());
		//然后开始循环遍历
		System.out.println("================================以下是循环遍历=========================================");
		while (iterator.isNotDone()) {
			try {
				System.out.println("聚合对象数组当前对象是:   " + iterator.getCurrent().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//此时换成string数组试试
		Object[] strs = {"aaa", "bbb", "ccc", "ddd", "eee", "fff"};
		Aggregate aggregate2 = new ConcreteAggregate(strs);
		Iterator iterator2 = aggregate2.getIterator();
		System.out.println("第一个是:   " + iterator2.frist().toString() + "  最后一个是:   " + iterator2.last().toString());
		//然后开始循环遍历
		System.out.println("================================以下是循环遍历=========================================");
		while (iterator2.isNotDone()) {
			try {
				System.out.println("聚合对象数组当前对象是:   " + iterator2.getCurrent().toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*
	 * 总结：
	 * 这就是迭代器模式
	 * 我们自定义了一个迭代器，然后把数组传入进去，就能进行我们的迭代遍历了，我们用了两种不同的数组，依旧能够遍历，这就是迭代器模式的优势
	 * 我们此时无论是什么聚合对象，我们只需要遍历它，那么就可以采用这种模式来实现
	 */
	
}
