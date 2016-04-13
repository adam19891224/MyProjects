package com.adam.Designs.structure.Composite.main;

import com.adam.Designs.structure.Composite.entity.Branch;
import com.adam.Designs.structure.Composite.entity.Leaf;
import com.adam.Designs.structure.Composite.interfaces.Root;

/**
 * 组合模式的演示
 * @author adam
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//创建两个叶子节点，还是用多态
		Root leaf1 = new Leaf();
		Root leaf2 = new Leaf();
		//调用他们的方法
		leaf1.sayHello();
		leaf2.sayHello();
		
		//创建组合对象，即树枝对象
		Root branch = new Branch();
		//把叶子对象传进去
		branch.getRoot().addLeaf(leaf1);
		branch.getRoot().addLeaf(leaf2);
		//调用branch的方法
		branch.sayHello();
		
	}
	
	/*
	 * 总结，我们发现，我们定义了两个不同的对象，一个是组合对象树枝，另一个是单立对象叶子，但是他们都可以用同样的方式来调用，即sayHello方法，并且没有任何影响
	 * 这就是组合模式的简单案例
	 */
}
