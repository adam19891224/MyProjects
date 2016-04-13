package com.adam.Designs.creater.Builder.entity;

/**
 * 创建者模式，这是实体类，里面有两个属性，一个方法
 * @author Administrator
 *
 */
public class Entity {

	private String color;
	private String size;
	
	public void say(){
		System.out.println("你好，我是实体对象，我的颜色是： " + color + ",  我的大小时： " + size);
		System.out.println();
	}
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
