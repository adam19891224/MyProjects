package com.adam.Designs.behaviour.Visitor.interfaces.impl;

import com.adam.Designs.behaviour.Visitor.interfaces.Element;
import com.adam.Designs.behaviour.Visitor.interfaces.Visitor;


/**
 * 元素实际类
 */
public class MyElement implements Element {

	@Override
	public void doSomeThing() {
		System.out.println("你好，我是MyElement");
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}

}
