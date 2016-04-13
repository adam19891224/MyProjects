package com.adam.Designs.structure.Adapter.adapter;

import com.adam.Designs.structure.Adapter.interfaces.InterfaceTarget;
/**
 * 此时我们让这个抽象类去实现接口的所有方法即可，抽象类的优势就是只需要直接实现接口，方法体为空也可以
 * 到时候我们的实际类就不用直接实现接口了，而是继承这个抽象类即可，因为父类的方法可以有选择性的覆盖，
 * 就不必像接口似的全部需要实现了，这个过程已经交给了抽象父类做
 * 
 * 但是也要注意一点，我们抽象父类可以不实现接口的所有方法，这并不会报错，但是一旦实际类继承了这个父类
 * 同样需要实现所有方法，所以如果抽象父类不实现接口的方法，那么就相当于毫无意义，所以这里我们就用抽象父类
 * 来实现所有方法，但是可以不做任何操作
 *
 */
public abstract class AbstractInterface implements InterfaceTarget {

	@Override
	public void show() {
	}

	@Override
	public void sayHello() {
	}

	@Override
	public void sayHi() {
	}

	@Override
	public void sayBye() {
	}

	@Override
	public void running() {
	}
	
	public void abstractFun(){
		System.out.println("你好");
	}

}
