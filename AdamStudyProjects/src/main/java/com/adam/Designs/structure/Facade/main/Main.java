package com.adam.Designs.structure.Facade.main;

import com.adam.Designs.structure.Facade.facade.Facade;

/**
 * 外观模式
 * @author adam
 * 2015.5.13
 */
public class Main {

	public static void main(String[] args) {

		//直接创建我们的外观接口对象
		Facade f = new Facade();
		//调用它提供的操作方法showTwoEntity
		f.showTwoEntity();
		
	}
	
	/*
	 * 总结
	 * 此时我们发现，我们并没有调用entityA和entityB的任何方法，但是程序却执行了它们内部的方法，而这到底是怎么回事呢？
	 * 原来我们调用了外观接口的showTWOentity方法，而这个方法才是实际上去调用的entityA与entityB的两个show方法
	 * 这样就实现了用户与系统的分离，因为用户完全不用去担心entityA与entityB的两个show方法具体是什么，我们仅仅需要做的就是
	 * 去调用给我们提供窗口的facade对象的方法就OK了 
	 */

}
