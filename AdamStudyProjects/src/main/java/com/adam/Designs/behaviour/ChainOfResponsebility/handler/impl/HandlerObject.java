package com.adam.Designs.behaviour.ChainOfResponsebility.handler.impl;

import com.adam.Designs.behaviour.ChainOfResponsebility.handler.Handler;

/**
 * handler实际处理对象
 *
 */
public class HandlerObject extends Handler {

	@Override
	public void handlerRequest() {//实现抽象类的处理请求方法
		if(getHandler() != null){//表示此时还有下一个handler对象，则此时当前handler就不处理这个请求
			System.out.println("放过请求，交给下一个handler处理");
			//继续调用hanlderRequest方法
			getHandler().handlerRequest();
		}else{
			//说明此时没有Hanlder了，则当前Handler就要处理这个请你
			System.out.println("正在处理请求，请稍等");
		}
		
	}

}
