package com.adam.Designs.behaviour.ChainOfResponsebility.main;

import com.adam.Designs.behaviour.ChainOfResponsebility.handler.Handler;
import com.adam.Designs.behaviour.ChainOfResponsebility.handler.impl.HandlerObject;

/**
 * 责任链模式演示
 * @author adam
 * 2015.5.19
 */
public class Main {

	public static void main(String[] args) {
		
		//定义三个处理链
		Handler han1 = new HandlerObject();
		Handler han2 = new HandlerObject();
		Handler han3 = new HandlerObject();
		
		//对han1传递han2,表示han2是han1的下一个对象，这就意味着han1并不会处理这个请求
		han1.setHandler(han2);
		//同样对han2传递han3，则此时han2也不会处理请求,并且由于han3没有下一个han对象了，则han3就是实际处理请求的对象
		han2.setHandler(han3);
		//调用han1的请求处理方法
		han1.handlerRequest();
		
	}
	
	/*
	 * 总结
	 * 此时我们发现，一共打印了两句放过请求，传递给下一个handler处理，和一句正在处理请求
	 * 这就和我们前面写的完全吻合
	 * 即我们总共传递了三个handler对象，其中给han1传递下一个对象han2,然后给han2传递下一个对象han3,这样的话，han1与han2都有下一个对象，则它们本身就不会对请求进行处理
	 * 然而最关键的是han3,此时han3并没有得到另一个han的传递，则此时就说明需要它来处理这个请求了，结果打印结果和我们预计的完全一样
	 * 打印的两句放过请求。。。就是请求经过han1与han2时调用的打印
	 * 而最后一句正在处理。。。就是han3实际处理的语句
	 * 
	 * 所以这就是责任链模式，至于我们客户端，并清楚是哪个handler来处理的，而且我们也不用关系是哪一个，只要它能处理并返回给我我想要的数据就行了
	 */
	
}
