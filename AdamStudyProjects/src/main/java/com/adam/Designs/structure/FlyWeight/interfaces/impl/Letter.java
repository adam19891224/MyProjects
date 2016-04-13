package com.adam.Designs.structure.FlyWeight.interfaces.impl;

import com.adam.Designs.structure.FlyWeight.interfaces.Word;

/**
 * word实现类，字母类
 *
 */
public class Letter implements Word {

	private String temp;
	
	public Letter(String c){
		this.temp = c;
	}
	
	public String getChar() {
		return this.temp;
	}

}
