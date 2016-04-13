package com.adam.Designs.creater.Singleton.entity;
/**
 * 单例模式之懒汉模式
 * 单例模式的特点是希望在整个环境中，有且只有一个自己的实例，并且这个实例能够给全局使用时，就需要采用单例模式，单例模式也是最简单的设计模式，它的特点是内部需要一个私有的构造方法
 * 这样做的目的是外界不能通过new关键字来创建任意多的对象，而只能通过该实体提供的getEntity方法来得到这个对象
 * 而懒汉模式的特点就是当我们调用getEntity的时候，类才会去创建这个对象，就相当于懒人一样，只有火烧眉毛的时候，才会做事
 * @author adam
 * 2015.5.5
 * 
 */
public class LazyMan {

	//私有构造函数
	private LazyMan(){
		
	}
	
	//全局变量
	private static LazyMan lazyMan = null;
	
	public static synchronized LazyMan getInstence(){
		//由于单例模式特点是全局只有一个对象，所以要先判空,并且是同步的方法
		if(lazyMan == null){
			lazyMan = new LazyMan();
		}
		return lazyMan;
	}
	
}
