package com.adam.Designs.structure.Adapter.adapter;

import com.adam.Designs.structure.Adapter.entity.Entity;
import com.adam.Designs.structure.Adapter.interfaces.Target;

/**
 * 适配器模式之对象适配器
 * 此时我们只实现接口，那么当我们去实现sayHello的方法时，我们创建一个entity类，让该方法去调用entity的sayHello方法，也能达到效果
 * @author adam
 * 2015.5.7
 *
 */
public class ObjectAdapter implements Target {
	
	//构造方法，在创建这个对象的同时，我们让客户端也要传递一个entity的对象进来
	private Entity entity = null;
	
	//这里做一个衍生，用我们前面从effictive JAVA中学到的构建器来创建该对象
	
	public static class Builder{
		
		private Entity tempEntity = null;
		
		//构建器中我们定义一个entity方法，目的是传入entity的对象,同时返回构建器对象
		public Builder entity(Entity e){
			//把这个对象赋值给内部类构建器的tempEntity
			this.tempEntity = e;
			return this;
		}
		
		//我们最关键的builder方法
		public ObjectAdapter builder(){
			return new ObjectAdapter(this);
		}
		
	}
	
	//私有构造方法，防止客户端直接new对象，并且在创建对象的时候，还需要传递一个构建器内部类
	private ObjectAdapter(Builder b){
		this.entity = b.tempEntity;
	}

	public void showMyName() {
		System.out.println("你好，我是目标接口，并且是对象适配器");
	}

	public void sayHello() {
		//不直接实现，而是让它调用entity的sayHello方法
		this.entity.sayHello();
	}

}
