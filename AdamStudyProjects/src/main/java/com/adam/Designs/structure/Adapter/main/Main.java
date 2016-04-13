package com.adam.Designs.structure.Adapter.main;

import com.adam.Designs.structure.Adapter.adapter.AbstractEntity;
import com.adam.Designs.structure.Adapter.adapter.ClassAdapter;
import com.adam.Designs.structure.Adapter.adapter.ObjectAdapter;
import com.adam.Designs.structure.Adapter.entity.Entity;
import com.adam.Designs.structure.Adapter.interfaces.InterfaceTarget;
import com.adam.Designs.structure.Adapter.interfaces.Target;

/**
 * 适配器模式使用简介
 * @author Administrator
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//classAdapter();
		
		objectAdapter();
	
		interfaceAdapter();
	}

	private static void objectAdapter() {
		//对象适配器的使用方法
		//同样先声明一个接口
		Target target = new ObjectAdapter.Builder().entity(new Entity()).builder();
		
		target.sayHello();
		target.showMyName();
		
		/*
		 * 总结
		 * 此时我们就不需要梳理了，总体步骤分为如下：
		 * 1.创建接口，并且编写两个方法，一个是接口自己的showMyName,另一个使我们希望使用的entity的sayHello方法
		 * 2.创建entity类，里面有一个sayHello方法
		 * 3.创建Adapter类，注意一点的是，此时Adapter只需要实现接口即可，因为在Adapter内部我们定义了一个entity的类对象，当
		 *   Adapter需要实现sayHello方法时，我们让它去调用entity的sayHello方法即可
		 *   所以实际上Adapter类只实现了接口的showMyName方法而已，而sayHello方法却是借花献佛，让entity对象去实现的
		 */
	}

	@SuppressWarnings("unused")
	private static void classAdapter() {
		//此时我们就可以通过定义一个Target的接口声明，并让它等于实现了它的Adapter实现类
		Target target = new ClassAdapter();
		
		//此时我们就可以调用entity的方法和target的方法了
		target.showMyName();
		target.sayHello();
		
		/*
		 * 总结：
		 * 我们首先梳理一下整个流程
		 *     ----我们有一个接口，里面本身只有一个方法，showMyName
		 *     ----我们有一个类，里面只有一个方法，sayHello
		 *     ####我们希望做的是做一个接口的对象，让此对象不仅能使用接口的showMyName方法，还能使用类的sayHello方法
		 *  
		 *     我们的做法就是：
		 *     1、在接口中显示的声明一下类的方法，即sayHello方法
		 *     2、创建一个适配器类Adapter，让它实现接口的同时，继承我们的类
		 *     3、此时Adapter就需要实现接口的所有方法（类的方法不用实现，因为可以直接用），但是此时由于我们接口声明的第二个方法sayHello正好是父类的方法，所以就相当于
		 *       父类帮我们实现了这个接口的一个方法了，那么此时我们的Adapter就只需要实现接口自己的showMyName方法即可
		 *     4、我们创建接口的对象，Target t = new Adapter（）；此时t就能调用自己的showMyName方法和类Entity的sayHello方法了
		 */
	}
	
	
	private static void interfaceAdapter(){
		InterfaceTarget target = new AbstractEntity();
		//此时我们没有实现show，但是也可以调用，结果当然是什么都不做
		target.show();
		//调用我们自己的running方法
		target.running();
		
	}
	
}
