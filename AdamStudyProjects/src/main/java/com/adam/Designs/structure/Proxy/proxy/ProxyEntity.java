package com.adam.Designs.structure.Proxy.proxy;

import com.adam.Designs.structure.Proxy.interfaces.Entity;
import com.adam.Designs.structure.Proxy.interfaces.impl.ConEntity;

/**
 * 这是我们的增强代理对象，同样实现对象接口，但是这里我们利用aop的方法，实现对原对象的方法扩展
 *
 */
public class ProxyEntity implements Entity {
	
	private ConEntity entity;
	
	public static class Builder{
		private ConEntity e;
		
		public Builder(){
			this.e = new ConEntity();
		}
		
		public ProxyEntity builder(){
			return new ProxyEntity(this);
		}
	}
	
	private ProxyEntity(Builder b){
		this.entity = b.e;
	}

	public void sayHello() {
		
		beforeFun();
		this.entity.sayHello();
		afterFun();
	}
	
	public void beforeFun(){
		System.out.println("我是增强前置方法");
	}
	
	public void afterFun(){
		System.out.println("我是增强后置方法");
	}

}
