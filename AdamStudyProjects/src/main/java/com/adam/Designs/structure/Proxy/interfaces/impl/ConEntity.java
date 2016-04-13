package com.adam.Designs.structure.Proxy.interfaces.impl;

import com.adam.Designs.structure.Proxy.interfaces.Entity;

/**
 * 实际的entity对象，这里也可以理解为原对象
 * @author adam
 *
 */
public class ConEntity implements Entity {

	public void sayHello() {
		System.out.println("你好，我是原对象");
	}

}
