package com.adam.Designs.creater.Builder.builder;

import com.adam.Designs.creater.Builder.entity.Entity;

/**
 * 创建者模式
 * 这种模式出现的原因是因为我们往往希望把对象的创建过程和使用过程进行区分，所以我们往往定义一个主导者来创建一个工厂实体，而得到具体的entity实体则交给这个工厂来做
 * 创建者模式适用于创建对象过程十分复杂，而对于使用相对独立的情况
 * 或者适用于在创建对象的过程中，可以根据不同的情况创建不同的表示对象
 * @author adam
 * 2015.4.30
 */
public interface Builder {

	//根据上面的概念，这就是一个创建者，这个创建者内部有三个方法，一个是给实体对象赋值颜色，另一个是给实体对象赋值大小，最后就是返回复制后的这个对象
	
	public void setColor(String type);
	
	public void setSize(String type);
	
	public Entity getEntity();
	
	
}
