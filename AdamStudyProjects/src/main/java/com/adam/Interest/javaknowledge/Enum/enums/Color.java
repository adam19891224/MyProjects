package com.adam.Interest.javaknowledge.Enum.enums;
/**
 * 枚举简单演示
 * @author adam
 * 2015.5.22
 */
public enum Color {

	/**
	 * 定义一些颜色枚举
	 */
	/**
	 * 红色
	 */
	RED("红色"),
	//RED, 直接报错，因为我们定义枚举时，就相当于创建对象了，而此时该对象的构造函数要求带参数
	/**
	 * 蓝色
	 */
	BLUE("蓝色"),
	/**
	 * 绿色
	 */
	GREEN("绿色"),
	/**
	 * 黄色
	 */
	YELLOW("黄色"),
	/**
	 * 粉红色
	 */
	PINK("粉红色");
	
	//反映自己颜色的字符串
	private String color;
	
	private Color(String s){
		this.color = s;
	}
	
	@Override
	public String toString() {
		return this.color;
	}
	
	public int add(int a, int b){
		return a + b;
	}
}
