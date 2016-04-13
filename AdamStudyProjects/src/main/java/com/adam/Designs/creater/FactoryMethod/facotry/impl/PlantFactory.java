package com.adam.Designs.creater.FactoryMethod.facotry.impl;

import com.adam.Designs.creater.FactoryMethod.entity.Entity;
import com.adam.Designs.creater.FactoryMethod.entity.impl.Plant;
import com.adam.Designs.creater.FactoryMethod.facotry.Factory;

/**
 * 该工厂作用是创建植物实体，所以命名为PlantFactory
 * @author Administrator
 *
 */
public class PlantFactory implements Factory {

	public Entity getEntity() {
		return new Plant();
	}

}
