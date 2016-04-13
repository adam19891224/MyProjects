package com.adam.Designs.structure.Adapter.interfaces;
/**
 * 接下来介绍第三种，即接口适配器的方式
 * 我们知道，通常采用接口编程的话，如果接口中定义的抽象方法过多，则实现它的实现类也必须要逐个去实现，这样对于我们来说
 * 就大大的浪费了时间，因为普遍情况是我们可能仅仅只需要其中的一个方法，所以此时做一个接口适配器就十分重要了
 * 我们的做法也很简单，采用一个抽象类去实现这些接口，而抽象类的方法不必有方法体，并且我们自己的类也不直接和接口打交道
 * 了，而是去继承抽象类，那么这时我们就可以根据自己的需求有选择性的覆盖父类的方法了
 * 
 * 
 * 就一下面来说，此时我们接口有5个方法，而此时我们创建一个实现类，如果实现这个接口的话，必然会让我们全部实现这5个方法
 * 然而我们只希望这个实现类只实现其中的running方法，那么就可以用上面提到的接口适配器来做
 *
 */
public interface InterfaceTarget {

	void show();
	
	void sayHello();
	
	void sayHi();
	
	void sayBye();
	
	void running();
	
}
