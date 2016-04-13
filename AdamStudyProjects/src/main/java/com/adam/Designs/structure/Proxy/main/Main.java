package com.adam.Designs.structure.Proxy.main;

import com.adam.Designs.structure.Proxy.proxy.ProxyEntity;

/**
 * 代理对象演示方法
 * @author adam
 *
 */
public class Main {

	public static void main(String[] args) {
		//我们直接创建代理对象
		ProxyEntity entity = new ProxyEntity.Builder().builder();
		
		entity.sayHello();
	}
	
	/*
	 * 总结
	 * 我们不难发现，代理模式可以让我们很轻易的改变原有的对象行为，这一点极其重要，有了这种模式，我们就能为一系列的
	 * 方法进行增强，或者给原对象进行减肥操作，这样不仅达到了我们所希望的效果，而且还不和源代码相冲突
	 *  
	 */
}
