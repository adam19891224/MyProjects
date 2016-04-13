package com.adam.Designs.creater.Prototype.main;

import com.adam.Designs.creater.Prototype.entity.Prototype;
import com.adam.Designs.creater.Prototype.entity.impl.ConcreteP1;
import com.adam.Designs.creater.Prototype.entity.impl.ConcreteP2;
import com.adam.Designs.creater.Prototype.manager.PrototypeManager;

/**
 * 原型模式演练
 *
 */
public class Main {

	//通过两个接口类型，得到两个原型对象，然后用同一个接口实例分别得到两个克隆，打印对象，我们观察一下结果：
	public static void main(String[] args) {

		//prototypeDemo();
		
		/**
		 * 接下来是第二种，即加入了原型管理对象
		 * 
		 */
		try {
			PrototypeManager pm = new PrototypeManager();
			//创建一个concreteP1
			Prototype p1 = new ConcreteP1();
			System.out.println("此时P1是：" + p1);
			pm.setPrototypeObject("p1", p1);
			
			Prototype pro1 = null;
			//此时用pro1来得到map对象
			pro1 = (Prototype) pm.getPrototypeObject("p1").clone();
			System.out.println("此时pro1是：" + pro1);
			
			//创建一个concreteP2
			Prototype p2 = new ConcreteP2();
			System.out.println("此时P2是：" + p2);
			pm.setPrototypeObject("p2", p2);
			
			//此时用pro1来得到p2
			pro1 = pm.getPrototypeObject("p2");
			System.out.println("此时pro1是：" + pro1);
			
			//然后销毁p2
			pm.destoryPrototypeObject("p2");
			//再次得到P2
			pro1 = pm.getPrototypeObject("p2");
			System.out.println("此时pro1又是：" + pro1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@SuppressWarnings("unused")
	private static void prototypeDemo() {
		Prototype p1 = new ConcreteP1();
		
		Prototype p2 = new ConcreteP2();
		//上面两个应该没有问题，分别是concreteP1和concreteP2的实例
		System.out.println("p1是:  " + p1);
		System.out.println("p2是：     " + p2);
		
		//接下来定义一个prototype的变量，然后分别得到两个对象的clone，在观察
		Prototype pro = null;
		pro = (Prototype) p1.clone();
		System.out.println("此时Pro是：     " + pro);
		pro = (Prototype) p2.clone();
		System.out.println("此时Pro是：     " + pro);
		
		//这里我们发现，同样是接口，此时只需要一个返回object的方法，同样能达到在某些时候得到想要的对象的情况
		//此时我们的接口类中并没有return ConcreteP1或者  return ConcreteP2的类似方法，而仅仅只有个返回object的clone方法
		//然后通过我们的两个实例，调用他们自身的clone方法，来让同一个接口类型pro变成了不同的对象
	}

}
