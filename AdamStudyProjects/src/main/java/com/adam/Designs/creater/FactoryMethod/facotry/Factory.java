package com.adam.Designs.creater.FactoryMethod.facotry;

import com.adam.Designs.creater.FactoryMethod.entity.Entity;

/**
 * 工厂方法模式，该模式适用于当一个类并不清楚它到底需要创建什么对象的时候
 * 		或者是一个类希望它的子类来根据需求实现具体的对象创建的时候
 * 
 *  该工程只有一个方法，就是返回一个Entity接口类型的对象,而至于到底创建什么实体，就交给实现它的工厂来决定
 * @author adam
 * 2015.4.29
 *
 */
public interface Factory {
	
	public Entity getEntity();
	
}
