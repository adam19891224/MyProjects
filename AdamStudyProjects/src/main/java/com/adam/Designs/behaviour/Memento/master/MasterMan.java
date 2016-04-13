package com.adam.Designs.behaviour.Memento.master;

import com.adam.Designs.behaviour.Memento.memento.Memento;

/**
 * 这是我们的主人类，主要是保存备忘录和得到备忘录的方法
 *
 */
public class MasterMan {

	
	//同时需要一个创建备忘录的方法，同时放入我们需要保持的备忘信息，
	//而这个方法需要返回创建的备忘录对象，至于为什么要返回对象，而不是直接void，因为后面我们需要把备忘录传递给操作者，所以操作者得到的对象不可能是我们
	//new的对象，那样就没数据了，而实际上操作者得到的对象就是下面这个方法返回的备忘录实体，这样才把整个线打通
	public Memento createMemento(String info){
		return new Memento(info);//同时把我们想要保存的信息传给备忘录对象
	}
	

	//展示我们记录的备忘信息,当然需要传入一个参数，即备忘录，如果不传参数，主人也不知道到底该取哪一个备忘录信息
	public String getMementoInfo(Memento memento){
		return memento.getInfos();//调用备忘录的getinfos方法得到备忘信息
	}
	
	//表示主人正在做事
	public void doThing(){
		System.out.println("正在做事....");
	}
}
