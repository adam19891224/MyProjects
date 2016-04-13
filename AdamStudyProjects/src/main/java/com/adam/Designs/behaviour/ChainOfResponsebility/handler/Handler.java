package com.adam.Designs.behaviour.ChainOfResponsebility.handler;
/**
 * 责任链模式
 * 该模式大致内容如下
 * 我们客户端发送一个请求之后，服务器会根据这个请求进行链式的传递，例如A接到请求后，可能传递给B，B接收请求后，可能传递给C，这样依次传递下去，但是也有可能是A直接就处理了请求，也
 * 有可能是B处理这个请求，总之具体谁处理，是服务器的事情，客户端并不需要关心，这样就达到了一个软件开发很重要的目的，解耦和
 * @author adam
 * 2015.5.19
 */
public abstract class Handler {

	//定义一个处理对象
	private Handler handler;
	
	//定义一个处理抽象方法
	public abstract void handlerRequest();
	
	//定义一个得到当前处理对象的方法
	public Handler getHandler(){
		return this.handler;
	}
	
	//定义一个向下传递的方法
	public void setHandler(Handler h){
		this.handler = h;
	}
}
