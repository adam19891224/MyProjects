package com.adam.Designs.behaviour.IteratorCase.main;

import com.adam.Designs.behaviour.IteratorCase.aggregate.impl.ConcreteAggregate;
import com.adam.Designs.behaviour.IteratorCase.iterator.Loop;

/**
 * 迭代器模式作业
 * @author adam
 * 2015.5.27
 */
public class Main {

	public static void main(String[] args) {
		//构建数组，这次我们用double, 当然，显示声明还是需要用Object的多态
		Object[] dous = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
		
		//得到迭代器
		Loop loop = new ConcreteAggregate(dous).loop();
		
		System.out.println("第一个是: " + loop.frist());
		System.out.println("最后一个是: " + loop.last());
		System.out.println("===============================================以下是循环=========================================");
		while (loop.hasNext()) {
			System.out.println("当前元素是：     " + loop.nextObj());
		}
	}
	
}
