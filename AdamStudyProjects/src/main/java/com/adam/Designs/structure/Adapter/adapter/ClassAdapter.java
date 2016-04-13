package com.adam.Designs.structure.Adapter.adapter;

import com.adam.Designs.structure.Adapter.entity.Entity;
import com.adam.Designs.structure.Adapter.interfaces.Target;

/**
 * 这就是我们的适配器
 * 此时我们可以简单的看到，此时我们有一个entity类，还有一个我们的目标接口，但是我们的entity并不是目标接口的实现类，此时我们又想在target接口中拥有类Entity的sayHello
 * 方法，此时又怎么办呢？
 * 就可以通过我们下面的适配器来解决，我们让适配器同时做两件事：
 * 1.实现target接口，此时适配器就拥有了showMyName方法
 * 2.继承我们的entity类，此时adapter就是entity的子类了，那么我们当然能通过定义Target e = new Adapter这种多态的方式来实现
 * 则此时我们的e不仅有了entity父类的sayHello方法，还有了target接口的showMyName方法
 * @author adam
 * 2015.5.7
 * 
 * 特别注意一下，此时我们这个是适配器取名为类适配器，顾名思义，它继承了Entity类
 * 
 * 接下来我们还会介绍一种适配器，叫对象适配器，它只负责实现接口，同时在它的内部定义一个entity的对象，当这个adapter实现sayHello方法时，我们让它调用entity的方法即可，相当于二次传递
 * 
 */
public class ClassAdapter extends Entity implements Target {

	public void showMyName() {
		System.out.println("你好，我是目标接口");
	}
	
	
	/*
	 * 注意，
	 * 由于我们的Adapter类是Entity的子类，且Entity内中正好有Target接口的sayHello方法，则此时我们就可以只需要实现Entity没有的showMyName方法即可
	 */
	

}
