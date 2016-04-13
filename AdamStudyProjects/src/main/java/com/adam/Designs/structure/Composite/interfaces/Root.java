package com.adam.Designs.structure.Composite.interfaces;

import com.adam.Designs.structure.Composite.entity.Branch;

/**
 * 组合模式
 * 该模式适用于把对象组合成树形结构的情况下，这种模式的特点就是我们使用单个对象或者他们的组合对象时，方法是一致的,即我们不会因为对象单个而会有特殊方法
 * 也不会因为对象组合成了组合对象而增加额外方法
 * @author adam
 * 2015.5.9
 *
 * 下方就是我们的root节点，这是一个接口类似，然后内部定义两个方法
 * 一个是返回我们树枝类型的get方法，另一个就是一个简单的打印方法
 *
 */
public interface Root {

	Branch getRoot();
	
	void sayHello();
	
}
