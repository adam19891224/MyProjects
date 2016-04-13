package com.adam.Designs.behaviour.InterpretCase.interfaces.impl;

import com.adam.Designs.behaviour.InterpretCase.context.Context;
import com.adam.Designs.behaviour.InterpretCase.interfaces.Expression;

/**
 * 加法解释器，根据上下文对象做加法
 *
 */
public class PlusExpression implements Expression {

	@Override
	public int interptet(Context context) {
		return context.getNum1() + context.getNum2();
	}

}
