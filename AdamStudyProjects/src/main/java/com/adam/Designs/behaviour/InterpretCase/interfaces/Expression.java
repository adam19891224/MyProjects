package com.adam.Designs.behaviour.InterpretCase.interfaces;

import com.adam.Designs.behaviour.InterpretCase.context.Context;

/**
 * 解释器作业，常用于针对上下文做一个解释
 * 这个概念可能有点模糊，我们这样说吧，解释器模式就相当于我们java做的一些编译器，比如此时我们创建一个解释器
 * 内部有一个解释方法，然后创建一个加法解释器，这个加法解释器实现解释方法就是做一个加法，此时创建一个减法解释器，这个
 * 减法解释器实现的解释方法就是减法
 * 当然，解释器是需要上下文支持的，所以我们还必须创建一个上下文，这里的上下文就是传入两个数num1和num2，然后调用加法
 * 解释器做加法解释，调用减法解释器做减法解释
 *
 */
public interface Expression {

	//这就是解释方法，传入上下文对象
	public int interptet(Context context);
	
}
