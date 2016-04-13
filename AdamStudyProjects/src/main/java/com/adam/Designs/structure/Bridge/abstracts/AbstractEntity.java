package com.adam.Designs.structure.Bridge.abstracts;

/**
 * 桥接模式
 * 该模式实际意义就是让抽象和实际分离，降低代码的耦合度
 * 但是该模式运用得并不多，只是这种思想被用于大众化的开发中
 * @author adam
 * 2015.5.8
 * 
 * 注意：
 * 该方法中会有一个传入我们实际的entity抽象类的方法
 *
 */
public abstract class AbstractEntity {

	public abstract void showMyName(MyEntity e);
	
}
