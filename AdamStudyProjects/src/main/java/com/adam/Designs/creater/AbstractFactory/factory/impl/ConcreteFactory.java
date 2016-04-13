package com.adam.Designs.creater.AbstractFactory.factory.impl;

import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityA;
import com.adam.Designs.creater.AbstractFactory.entity.AbstractEntityB;
import com.adam.Designs.creater.AbstractFactory.entity.impl.ConcreteEntityA;
import com.adam.Designs.creater.AbstractFactory.entity.impl.ConcreteEntityB;
import com.adam.Designs.creater.AbstractFactory.factory.AbstractFactory;

/**
 * 抽象工厂的具体实现工厂，实现了抽象工厂的两个创建对象的方法，这里要注意一点的是，抽象工厂的方法中
 * 方法的返回类型是entity的接口类，即AbStractEntityA和AbStractEntityB，但是我们在
 * 方法体中，返回的实际值确实entity接口的相应的实现类ConcreteEntityA和ConcreteEntityB
 * @author adam
 *
 */
public class ConcreteFactory implements AbstractFactory {

	//注意，这里方法的返回类型我们定义的是接口类型，但是实际返回的值确是接口的实现类
	public AbstractEntityA getEntityA() {
		return new ConcreteEntityA();
	}

	//同上
	public AbstractEntityB getEntityB() {
		return new ConcreteEntityB();
	}

}
