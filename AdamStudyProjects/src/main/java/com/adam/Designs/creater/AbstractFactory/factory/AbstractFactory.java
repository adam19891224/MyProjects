package com.adam.Designs.creater.AbstractFactory.factory;

import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityA;
import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityB;

/**
 * 抽象工厂模式：
 * 抽象工厂模式适用于系统本身与产品对象相对独立的情况，此时我们无须关心产品对象的创建过程，怎么创建的等等，同时
 * 我们也不需要关系产品对象中的某个方法的具体实现是怎么做的，我们仅仅只需要做两件事即可：
 * 		1.通过提供的对象工厂，创建相应的对象实例
 * 		2.通过对象实例调用相应的方法
 * @author adam
 * 2015.4.28
 *
 */

/**
 * 抽象工厂类，其实质是一个接口，内部有两个方法，一个是得到entityA的抽象类，另一个是得到entityB的抽象类
 * 
 */
public interface AbstractFactory {

	public AbstractEntityA getEntityA();
	
	public AbstractEntityB getEntityB();
}
