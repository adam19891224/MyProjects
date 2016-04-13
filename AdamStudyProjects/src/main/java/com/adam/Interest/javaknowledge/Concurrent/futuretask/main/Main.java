package com.adam.Interest.javaknowledge.Concurrent.futuretask.main;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.adam.Interest.javaknowledge.Concurrent.futuretask.FutureTaskText;

/**
 * 将来任务演示
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//这是我们的主线程
		System.out.println("银行：       先生您好，欢迎来到银行");
		Thread.sleep(1000);
		System.out.println("我：           你好，我要存50万");
		Thread.sleep(2000);
		System.out.println("银行：       好的，我需要确认一下，您先填表可以吗？");
		Thread.sleep(2000);
		System.out.println("我：           好的");
		//调用任务，注意用futureTase封装
		FutureTaskText taskText = new FutureTaskText();
		FutureTask<Object> futureTask = new FutureTask<Object>(taskText);
		//开启线程
		Thread thread = new Thread(futureTask);
		thread.start();
		//用while循环监控futureTask任务的isDone方法，看是否完成,如果没有完成，就打印提示
		while(!futureTask.isDone()){
			Thread.sleep(1000);
			System.out.println("======对不起，电脑还在执行请求，请稍等");
		}
		//接收返回的参数
		String res = (String) futureTask.get();
		System.out.println(res);
		System.out.println("银行：          好的，先生，您的提交已经成功");
	}
	
}
