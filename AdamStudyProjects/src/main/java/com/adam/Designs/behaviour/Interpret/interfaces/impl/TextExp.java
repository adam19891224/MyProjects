package com.adam.Designs.behaviour.Interpret.interfaces.impl;

import com.adam.Designs.behaviour.Interpret.context.Context;
import com.adam.Designs.behaviour.Interpret.interfaces.Expression;

/**
 * 文本解释器
 *
 */
public class TextExp implements Expression {
	
	@SuppressWarnings("unused")
	private String type;
	
	public TextExp(String t){
		this.type = t;
	}

	@Override
	public boolean interpret(Context context) {
		return true;
	}

}
