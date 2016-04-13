package com.adam.Designs.structure.FlyWeight.factory;

import java.util.Map;

import com.adam.Designs.structure.FlyWeight.interfaces.Word;
import com.adam.Designs.structure.FlyWeight.interfaces.impl.Letter;

/**
 * 享元模式
 * 即我们通常会把经常使用的常量保存起来，以达到减小内存开销的目的，这种类似的设计模式就叫享元模式，即分享同一个元，例如我们常用的26个字母
 * 我们不用每次使用都去创建一个字母的char型，而是通过一个map来管理，如果第一次出现，就创建这个字母，如果后面多次使用，就从map中取得这个字母
 * 即可
 * 享元模式常用场景
 * 1、一个系统中有大量对象，并且这些对象很多都重复
 * 2、由于对对象的大量使用，造成了内存的开销增大
 * 3、对象的状态大多数都是外部的状态，其实质确实几乎固定的
 * @author adam
 * 2015.5.14
 *
 */
public class FlyWeightFactory {

	//提供一个map来管理对象
	private static Map<String, Object> map;
	
	//创建工厂
	@SuppressWarnings("static-access")
	private FlyWeightFactory(Builder b){
		this.map = b.map;
	}
	
	//构造器
	public static class Builder{
		private Map<String, Object> map;
		
		public Builder map(Map<String, Object> map){
			this.map = map;
			return this;
		}
		public FlyWeightFactory build(){
			return new FlyWeightFactory(this);
		}
	}
	
	//创建得到字母的方法
	public Word getWord(String s){
		if(map.containsKey(s)){
			return (Word) map.get(s);
		}else {
			map.put(s, new Letter(s));
			return (Word) map.get(s);
		}
	}
	
	public void checkMap(){
		for(Map.Entry<String, Object> entry : map.entrySet()){
			System.out.println("key: " + entry.getKey() + "        value: " + entry.getValue());
		}
	}
	
	
}
