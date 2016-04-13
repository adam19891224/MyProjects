package com.adam.Designs.behaviour.Interpret.interfaces;

import com.adam.Designs.behaviour.Interpret.context.Context;

/**
 * 解释器模式
 * 解释器模式主要作用于我们自定义某种语言后，给定一种文法标准，然后编写解释器进行解析
 * 举个简单例子，我们做搜索功能时，解释器就可以去解析我们的分词词库，然后我们就只需要把传入的句子放入解释器中，通过解释器的方法来按照它解析的词库进行分词即可
 * @author adam
 * 2015.5.25
 *
 */
public interface Expression {

	//这里我们定义一个抽象表达式接口，内部就有两个方法，一个是根据上下文环境，制定这个解释器的表达式
	public boolean interpret(Context context);
	
	//判断是否相同的equal方法
	public boolean equals(Object o);
	
}
