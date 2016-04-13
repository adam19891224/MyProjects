package com.adam.Designs.behaviour.Memento.mementotaker;

import com.adam.Designs.behaviour.Memento.memento.Memento;

/**
 * 备忘录操作者，内部只有两个方法，创建备忘录信息，取出备忘录信息
 *
 */
public class MementoTaker {

	//声明备忘录对象，用来保存主人传入的备忘录
	private Memento memento;
	
	//保存备忘录
	public void saveMemento(Memento memento){
		this.memento = memento;
	}
	
	//把备忘录返回给主人
	public Memento getMemento(){
		return this.memento;
	}
	
}
