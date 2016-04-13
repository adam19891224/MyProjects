package com.adam.Designs.behaviour.Template.interfaces;
/**
 * 模板方法模式
 * 这种模式的大体思想就是我们定义一个模板框架，里面只需要声明一些必要的方法即可，然而具体的实现就根据不同的情况来做
 * 
 * 举个例子
 * 此时我们有个系统，它的作用是打印日志，但是此时我们要求这个系统在win, linux, mac os上都能够运行，那么此时我们就可以先做一个这个系统的模板框架，内部包含一个打印日志的方法即可
 * 然后我们再分别编写3套对应系统的模板实现，此时就完成了，当我们的系统在什么环境运行时，就调用相应的模板实现即可。
 * @author adam
 * 2015.6.4
 */
public interface Template {

	//内部包含一个打印日志方法
	public void showLog();
	
}
