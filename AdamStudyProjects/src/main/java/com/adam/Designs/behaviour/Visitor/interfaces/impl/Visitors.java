package com.adam.Designs.behaviour.Visitor.interfaces.impl;

import com.adam.Designs.behaviour.Visitor.interfaces.Visitor;


/**
 * 此时创建my元素的访问者
 * @author adam
 * 2015.8.21
 */
public class Visitors implements Visitor {

	//由于访问者会访问元素的某个方法，所以这里我们就访问元素的dosomething方法
	@Override
	public void visit(MyElement e) {
		//针对myelement元素做扩展
		System.out.println("访问者来啦。。。。");
		e.doSomeThing();
	}

	@Override
	public void visit(YourElement e) {
		e.doSomeThing();
	}

}
