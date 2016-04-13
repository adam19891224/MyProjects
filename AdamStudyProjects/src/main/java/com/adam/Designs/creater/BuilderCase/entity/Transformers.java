package com.adam.Designs.creater.BuilderCase.entity;

public class Transformers {

	private String color;
	private String weappon;
	
	public void success(){
		System.out.println("变身成功，我的颜色是：" + color + "   我的武器是: " + weappon);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getWeappon() {
		return weappon;
	}

	public void setWeappon(String weappon) {
		this.weappon = weappon;
	}
	
}
