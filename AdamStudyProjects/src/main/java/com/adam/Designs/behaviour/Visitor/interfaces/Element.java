package com.adam.Designs.behaviour.Visitor.interfaces;



/**
 * 访问者模式
 * 	
 * 	当元素的内部数据已经确定的时候，而我们需要做的是对这些数据进行处理，而不改变数据本身，此时就可以采用访问者模式
 * 	举个例子，假设此时一个元素有一个select方法，这个方法返回所有字段，但是此时我们希望对这些字段进行筛选，则此时在
 *  不修改select方法的前台下，就可以创建一个访问者来访问这个方法，同时在访问方法中进行筛选处理，而我们调用的时候就不必
 *  调用select方法了，而是调用这个访问者的访问方法即可
 * 	
 * @author adam
 * 2015.8.21
 */
public interface Element {

	//访问者模式中，数据通常会有两个甚至更多的方法，但是必然有一个accept方法来接收访问者
	
	//第一个方法
	public void doSomeThing();
	
	//第二个方法, 接收访问者
	public void accept(Visitor v);
	
}
