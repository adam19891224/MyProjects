package com.adam.Designs.structure.Adapter.interfaces;

/**
 * 这是我们的接口，此时我们有一个需求，即希望此接口创建的对象，不仅能够有下面的showMyName方法，同时还能拥有Entity类的sayHello方法，则怎么办呢？
 * 解决方法就是我们定义一个适配器，让此适配器实现这个接口的同时，又去继承我们的Entity类，则此时Adapter就同时拥有了showMyName方法（来自target接口）
 * 和sayHello方法（来自entity），则我们可以用多态的方式，定义Target target = new Adapter()这个Adapter类的对象，则此时target就能同时使用
 * target接口和Entity类的方法了
 * @author adam
 * 2015.5.7
 *
 */
public interface Target {

	/**
	 * 这是我们的目标接口
	 */
	public void showMyName();
	
	/**
	 * 这是Entity类的sayHello方法，我们这里也要声明
	 */
	public void sayHello();
	
}
