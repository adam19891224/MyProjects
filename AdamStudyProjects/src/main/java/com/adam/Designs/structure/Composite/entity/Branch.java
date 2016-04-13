package com.adam.Designs.structure.Composite.entity;

import java.util.ArrayList;

import com.adam.Designs.structure.Composite.interfaces.Root;

/**
 * 树枝节点，同样实现root接口
 * 这就是我们的组合对象了，它内部定义一个叶子的List集合
 * @author Administrator
 *
 */
public class Branch implements Root {

	//注意，这里返回类型就需要变成Branch了，而不是Root
	public Branch getRoot() {
		return this;
	}

	//叶子节点集合
	private ArrayList<Root> list = new ArrayList<Root>();
	
	//对list的set和get
	public void addLeaf(Root l){
		this.list.add(l);
	}
	
	public void removeLeaf(Root f){
		this.list.remove(f);
	}
	
	public void sayHello() {
		System.out.println("你好，我是树枝节点");
		//同时遍历list并调用sayHello方法
		for(Root l : this.list){
			l.sayHello();
		}
	}
	
}
