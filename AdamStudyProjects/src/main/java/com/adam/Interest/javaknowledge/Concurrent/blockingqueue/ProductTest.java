package com.adam.Interest.javaknowledge.Concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * BlockQueue队列测试
 * 这是一个支持两个附加操作的队列，即这是一个容器，如果内部的对象为空，其他调用这个队里的poll()方法，即得到队列内部的内容的所有线程都会被挂起等得，如果此时队列的对象沾满了整个队列，
 * 调用这个队列的put()方法，即向队列内部放入元素的所有线程都会被挂起等待
 * 
 * 这里我们就模拟一个生产者---消费者的模式
 * 此时有一个面包仓库，生产者不停向里面生产面包，而消费者源源不断的买面包，当仓库满时，提示生产者暂时不要生产了，当仓库空时，提示消费者等待
 * @author adam
 * 2015.5.29
 *
 */
public class ProductTest implements Runnable {

	//声明一个blockingqueue队列，即上面的面包仓库,我们最多只允许放入5个面包
	private BlockingQueue<String> breadLib = null;
	
	public ProductTest(BlockingQueue<String> queue){
		this.breadLib = queue;
	}
	
	/**
	 * 生产面包的方法
	 */
	public void createBread(){
		//用一个无限循环来做
		while(true){
			try {
				//生产者，不停向上面的仓库生产面包
				Thread.sleep(new Random().nextInt(20) * 1000);//设置暂停时间，模拟生产的随机性
				if(this.breadLib.size() == 5){
					System.out.println("面包仓库已满，请暂时不要再生产面包了");
				}else{
					System.out.println("【面包师】    ： 生产了一个面包, 当前面包总数  ：" + (this.breadLib.size() + 1));
				}
				breadLib.put("面包");//放入面包
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void run() {
		this.createBread();
	}
}