package com.adam.Designs.structure.Bridge.main;

import com.adam.Designs.structure.Bridge.implement.AbstractEntityObj;
import com.adam.Designs.structure.Bridge.implement.MyEntityOne;
import com.adam.Designs.structure.Bridge.implement.MyEntityTwo;

/**
 * 桥接模式的简单演示
 *
 */
public class Main {
	
	public static void main(String[] args) {
		//创建一个抽象类子类对象
		AbstractEntityObj abstractEntityObj = new AbstractEntityObj();
		abstractEntityObj.showMyName(new MyEntityOne());
		abstractEntityObj.showMyName(new MyEntityTwo());
	}
	
	/*
	 * 总结
	 * 这里我们发现并没有什么不同，但是这种模式的特点就是，如果此时我们在MyEntityOne或者MyEntityTwo中添加其他的任意方法，那么上面这段代码依旧不会影响
	 * 运行，这样就实现了抽象类与实际类的解耦操作
	 * 注意，这种模式虽然已经用的不多了（我们完全可以用接口来代替），但是它的中心思想却影响着一代一代的程序员
	 */
}
