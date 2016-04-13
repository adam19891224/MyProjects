package com.adam.Designs.behaviour.InterpretCase.interfaces.impl;

import com.adam.Designs.behaviour.InterpretCase.context.Context;
import com.adam.Designs.behaviour.InterpretCase.interfaces.Expression;

/**
 * 减法解释器，根据上下文做减法
 *
 */
public class MinusExpression implements Expression {

	@Override
	public int interptet(Context context) {
		return context.getNum1() - context.getNum2();
	}

}
