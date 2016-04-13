package com.adam.Interest.javaknowledge.Concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class CustomerTest implements Runnable {

	private BlockingQueue<String> breadLib = null;
	//采用构造函数初始化
	public CustomerTest(BlockingQueue<String> queue){
		this.breadLib = queue;
	}
	
	/**
	 * 购买面包方法
	 */
	public void buyBread(){
		while(true){
			try {
				Thread.sleep(new Random().nextInt(20) * 1000);
				if(this.breadLib.size() > 0){
					System.out.println("（消费者）：     购买了一个面包");
				}else{
					System.out.println("面包不足，请耐心等待");
				}
				this.breadLib.take();//取出面包
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		this.buyBread();
	}
	
}
