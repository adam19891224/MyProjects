package com.adam.Designs.creater.Singleton.entity;
/**
 * 单例模式之饿汉模式
 * 单例模式的特点就是希望在整个运行环境中，至始至终都只有一个对象实例，并且该对象能够给全局的所有成员使用，此时就可以采用单例模式，单例模式也是最简单的设计模式，特点就是内部包含
 * 一个私有的构造方法，这样就保证了除了自己之外，其他人不能通过new来创建对象，印证了至始至终都只有一个对象实例这句话，而要想得到对象，只能通过该类自己提供的方法来得到，并且该方法
 * 还需要判空，只有当对象为空时，才new。
 * 饿汉模式的特点就是在内部直接创建一个对象，无论有没有人使用，都会先创造在内存中，一旦有人要，就返回给他，就像饿汉一样，不管有没有，先吃了再说
 * @author adam
 * 2015.5.5
 *
 */
public class HungryMan {
	
	//私有构造函数
	private HungryMan(){
		
	}
	
	//全局对象
	private static final HungryMan hungryMan = new HungryMan();
	
	//返回的方法
	public static HungryMan getInstence(){
		return hungryMan;
	}

}
