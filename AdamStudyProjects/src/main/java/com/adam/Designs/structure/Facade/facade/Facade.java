package com.adam.Designs.structure.Facade.facade;

import com.adam.Designs.structure.Facade.entity.EntityA;
import com.adam.Designs.structure.Facade.entity.EntityB;

/**
 * 外观模式
 * 该模式作用是提供一个简单的接口，来实现复杂子系统的统一管理，从而让用户与系统完全解耦，用户想使用系统，只需要从这个接口来操作即可
 * 它的使用场合如下：
 * 1.给复杂的系统提供简单的访问接口
 * 2.客户端程序与抽象类的部分分离（完全分离显然是不实际的）
 * 3.如果此时我们创建的是一个层次级系统，则外观模式可以用来提供入口
 * 
 * @author Administrator
 *
 */
public class Facade {

	//此时就是外观模式，假设此时我们的entityA和entityB是复杂的子系统，那么我们如何能简单的方法它们的show方法呢？
	//显然，这里我们统一提供一个方法，然后让这个类的这个方法去调用A和B的show方法
	public void showTwoEntity(){
		EntityA.show();
		EntityB.show();
	}
	
}
