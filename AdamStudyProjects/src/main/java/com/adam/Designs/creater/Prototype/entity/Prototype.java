package com.adam.Designs.creater.Prototype.entity;
/**
 * 原型模式
 * 有时候，我们需要在运行的同时，制定某一个对象是属于哪一个类的，在这种时候，工厂模式就不太好了，因为它必须要提前在工厂中定义我们的所有类型，才能在实际运行中根据不同的业务逻辑
 * 做出不同的getEntity方法，显然这样太麻烦，而原型模式就是解决这种问题的解决方案，它通过一个叫clone的方法，来复制一个克隆对象，给现在的对象使用，这样就能在运行时动态改变
 * 对象的类所属了
 * @author adam
 * 2015.5.4
 *
 */
public interface Prototype {

	//原型对象，内部只含有一个方法
	public Object clone();
	
}
