package com.adam.Designs.creater.Builder.main;

import com.adam.Designs.creater.Builder.builder.Builder;
import com.adam.Designs.creater.Builder.builder.impl.ConcreteBuilder;
import com.adam.Designs.creater.Builder.entity.Entity;

/**
 * 创建者模式演练过程
 *
 */
public class Main {

	public static void main(String[] args) {
		//得到创建者的实体，依然多态
		Builder builder1 = new ConcreteBuilder();
		
		//由于采用了构造方法创建对象，所以此时bulider中就有一个entity实体被创建
		//设置颜色
		builder1.setColor("绿色");
		//设置大小
		builder1.setSize("中型");
		
		//然后得到这个对象
		Entity e1 = builder1.getEntity();
		
		//调用它的方法
		e1.say();
		
		//再创建第二个
		Builder builder2 = new ConcreteBuilder();
		builder2.setColor("黄色");
		builder2.setSize("巨型");
		
		Entity e2 = builder2.getEntity();
		e2.say();
		
		/*
		 * 总结：
		 * 正如我们前面概念所讲的，我们作为主导者，仅仅做 的事情是创建一个实体工厂，然后根据工厂中的方法设置一些我们希望的属性参数
		 * 最后接收这个工厂的返回值，调用返回对象的方法就OK了
		 * 此时正好印证了概念中的一句话，我们独立了对象的创建和使用两个过程，对象的创建交给了工厂类ConcreteBuilder，而使用确是我们自己调用了实体的say方法
		 * 并且这个创建过程十分复杂，不仅要设置颜色，还要设置大小，但是这都是工厂类完成的，我们自己不能进行设置，同样的，工厂设置完后，也只能将实体返回给我们，工厂同样
		 * 没有使用这个实体的权利
		 */
		
	}
}
