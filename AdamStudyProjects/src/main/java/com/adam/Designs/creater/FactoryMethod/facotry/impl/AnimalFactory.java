package com.adam.Designs.creater.FactoryMethod.facotry.impl;

import com.adam.Designs.creater.FactoryMethod.entity.Entity;
import com.adam.Designs.creater.FactoryMethod.entity.impl.Animal;
import com.adam.Designs.creater.FactoryMethod.facotry.Factory;

/**
 * 该工厂的作用是创建动物实体，所以命名为AnimalFactory
 */
public class AnimalFactory implements Factory {

	public Entity getEntity() {
		return new Animal();
	}

}
