package com.adam.Designs.behaviour.InterpretCase.main;

import com.adam.Designs.behaviour.InterpretCase.context.Context;
import com.adam.Designs.behaviour.InterpretCase.interfaces.Expression;
import com.adam.Designs.behaviour.InterpretCase.interfaces.impl.MinusExpression;
import com.adam.Designs.behaviour.InterpretCase.interfaces.impl.PlusExpression;

/**
 * 解释器模式作业
 *
 */
public class Main {
	
	public static void main(String[] args){
		
		//创建上下文
		Context context = new Context();
		//赋值
		context.setNum1(50);
		context.setNum2(30);
		
		//创建加法解释器
		Expression plusExpression = new PlusExpression();
		int res = plusExpression.interptet(context);
		System.out.println("plusExpression   结果是：  " + res);
		
		//创建减法解释器
		Expression minusExpression = new MinusExpression();
		res = minusExpression.interptet(context);
		System.out.println("minusExpression   结果是:  " + res);
		
	}
}

