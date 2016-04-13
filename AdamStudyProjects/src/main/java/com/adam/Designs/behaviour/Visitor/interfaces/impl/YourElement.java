package com.adam.Designs.behaviour.Visitor.interfaces.impl;

import com.adam.Designs.behaviour.Visitor.interfaces.Element;
import com.adam.Designs.behaviour.Visitor.interfaces.Visitor;


public class YourElement implements Element {

	@Override
	public void doSomeThing() {
		System.out.println("你好，我是YourElement");
	}

	//实现接口的接收访问者方法，通常这个方法内部会去调用访问者的访问方法，并且访问方法又要求传入元素接口对象
	//所以通常我们就是用visit（this）来传递当前元素
	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
