package com.adam.Designs.creater.BuilderCase.main;

import com.adam.Designs.creater.BuilderCase.builder.Builder;
import com.adam.Designs.creater.BuilderCase.builder.impl.ConcreteBuilder;
import com.adam.Designs.creater.BuilderCase.entity.Transformers;

public class Main {

	public static void main(String[] args) {
		//首先创建一个实体
		Transformers qingtianzhu = null;
		
		//创建一个创建者
		Builder builder = null;
		//利用多态得到实例
		builder = new ConcreteBuilder();
		//给变形金刚赋值
		builder.setColor();
		builder.setWeappon();
		//得到返回结果
		qingtianzhu = builder.getEntity();
		
		qingtianzhu.success();
		
	}
}
