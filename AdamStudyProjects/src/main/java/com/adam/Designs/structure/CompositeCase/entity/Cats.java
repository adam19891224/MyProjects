package com.adam.Designs.structure.CompositeCase.entity;

import java.util.List;

import com.adam.Designs.structure.CompositeCase.interfaces.Animal;
import com.adam.Utils.Utils;


/**
 * 组合模式作业，这里假设这个类是猫科类
 *
 */
public class Cats implements Animal {

	public Cats getCats() {
		return this;
	}

	//创建一个老虎的集合
	private List<Tiger> list = Utils.getList();
	
	public void addTiger(Tiger t){
		this.list.add(t);
	}
	
	public void showMy() {
		System.out.println("我是猫科动物");
		for(Tiger t : list){
			t.showMy();
		}
	}

}
