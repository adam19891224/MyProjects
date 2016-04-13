package com.adam.Designs.structure.Composite.entity;

import com.adam.Designs.structure.Composite.interfaces.Root;

/**
 * 叶子节点，实现ROOT接口
 * @author adam
 *
 */
public class Leaf implements Root {

	public Branch getRoot() {
		return null;
	}

	public void sayHello() {
		System.out.println("你好，我是叶子");
	}

}
