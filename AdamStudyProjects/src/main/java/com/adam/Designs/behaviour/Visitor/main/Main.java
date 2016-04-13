package com.adam.Designs.behaviour.Visitor.main;

import com.adam.Designs.behaviour.Visitor.interfaces.Element;
import com.adam.Designs.behaviour.Visitor.interfaces.Visitor;
import com.adam.Designs.behaviour.Visitor.interfaces.impl.MyElement;
import com.adam.Designs.behaviour.Visitor.interfaces.impl.Visitors;
import com.adam.Designs.behaviour.Visitor.interfaces.impl.YourElement;


public class Main {

	public static void main(String[] args) {
		
		//此时我们就创建两个元素对象
		Element my = new MyElement();
		Element your = new YourElement();
		
		//此时创建一个访问者
		Visitor v = new Visitors();
		
		//此时v需要访问my元素，则调用my元素的接收访问者方法
		my.accept(v);
		
		//同样，此时Your元素要被访问者方法，所以接收v
		your.accept(v);
		
		
		//这就是一个简单的访问者模式，我们用一个访问者去调用了两个元素各自的方法
		//此时如果我们需要扩展，则根本不必去动元素的任何方法(如果第一次用，需要在元素中添加accept方法)，
		//而剩下的事情就在visitor对象的visit方法中修改即可
		
		
		//做个简单的演示，假设此时我们需要对my元素扩展，同时打印一句访问者来了。则my元素不需要做任何修改，直接进入
		//visitor的visit(Myelement e)方法中修改即可
		
	}
	
}
