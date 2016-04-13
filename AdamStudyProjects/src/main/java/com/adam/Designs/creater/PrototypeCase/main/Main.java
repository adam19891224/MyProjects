package com.adam.Designs.creater.PrototypeCase.main;

import com.adam.Designs.creater.PrototypeCase.entity.Animal;
import com.adam.Designs.creater.PrototypeCase.entity.impl.Dog;
import com.adam.Designs.creater.PrototypeCase.entity.impl.Tiger;
import com.adam.Designs.creater.PrototypeCase.manager.AnimalManager;

/**
 * 原型模式的作业演练
 * 
 */
public class Main {
	
		
	public static void main(String[] args) {
		
		try {
			//得到对象管理容器
			AnimalManager animalManager = new AnimalManager();
			
			Animal tiger = new Tiger();
			animalManager.setAnimal("tiger", tiger);
			
			Animal dog = new Dog();
			animalManager.setAnimal("dog", dog);
			
			//准备工作已经完成，接下来创建一个动物接口声明
			Animal animal = null;
			animal = animalManager.getAnimal("tiger");
			
			//打印此时animal的toString
			System.out.println(animal.toString());
			
			animal = animalManager.getAnimal("dog");
			System.out.println(animal.toString());
			
			animalManager.remove("tiger");
			System.out.println("删除tiger成功");
			Thread.sleep(1000);
			
			animal = animalManager.getAnimal("tiger");
			System.out.println(animal.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
