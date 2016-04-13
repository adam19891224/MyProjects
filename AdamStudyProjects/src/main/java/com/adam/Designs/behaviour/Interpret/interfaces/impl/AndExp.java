package com.adam.Designs.behaviour.Interpret.interfaces.impl;

import com.adam.Designs.behaviour.Interpret.context.Context;
import com.adam.Designs.behaviour.Interpret.interfaces.Expression;

/**
 * 解释器实现类，这个类的作用是做and比较
 */
public class AndExp implements Expression {
	
	//首先定义两个解释器变量
	private Expression left;
	private Expression right;
	
	public AndExp(Expression l, Expression r){
		this.left = l;
		this.right = r;
	}

	//传入上下文，验证两个表达式的上下文是否一致
	@Override
	public boolean interpret(Context context) {
		return left.interpret(context) && right.interpret(context);
	}

	@Override
	public boolean equals(Object obj) {//这就是重新我们的equal方法，也就是说此时我们定义了解释器接口的equal方法要用这个语法来解析
		//验证
		if(obj != null && obj instanceof AndExp){
			return this.left.equals(((AndExp) obj).left) && this.right.equals(((AndExp) obj).right);
		}
		return false;
	}
	
}
