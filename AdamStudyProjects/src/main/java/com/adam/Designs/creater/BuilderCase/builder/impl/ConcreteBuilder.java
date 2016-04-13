package com.adam.Designs.creater.BuilderCase.builder.impl;

import com.adam.Designs.creater.BuilderCase.builder.Builder;
import com.adam.Designs.creater.BuilderCase.entity.Transformers;

public class ConcreteBuilder implements Builder {

	private Transformers tran = null;
	
	public ConcreteBuilder(){
		this.tran = new Transformers();
	}

	public void setColor() {
		try {
			tran.setColor("蓝色");
			System.out.println("我来组成颜色.....");
			Thread.sleep(1000);
			System.out.println("颜色组成成功");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setWeappon() {
		try {
			tran.setWeappon("加农炮");
			System.out.println("我来组成武器.....");
			Thread.sleep(1000);
			System.out.println("武器组成成功");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Transformers getEntity() {
		return this.tran;
	}
	
}
