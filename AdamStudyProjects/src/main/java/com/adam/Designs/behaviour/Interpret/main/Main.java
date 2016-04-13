package com.adam.Designs.behaviour.Interpret.main;

import com.adam.Designs.behaviour.Interpret.interfaces.Expression;
import com.adam.Designs.behaviour.Interpret.interfaces.impl.AndExp;
import com.adam.Designs.behaviour.Interpret.interfaces.impl.TextExp;

/**
 * 解释器演示
 * @author adam
 * 2015.5.25
 */

public class Main {

	public static void main(String[] args) {
		//解释器
		Expression expression = new AndExp(new TextExp("sss"), new TextExp("b"));
		System.out.println(expression.equals(expression));
	}
	
	
	/*
	 * 总结: 此时我们发现，结果和我们所想的一致，解释器模式就是这样一种模式，它根据我们定义的表达式，实现想要的解析语法，这里我们定义的解释语法就是equal。这样我们传入的两个对象
	 * 就会采用我们定义的解释语法来执行equal方法 
	 * 
	 */
}
