package com.adam.Interest.javaknowledge.Concurrent.blockingqueue.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.adam.Interest.javaknowledge.Concurrent.blockingqueue.CustomerTest;
import com.adam.Interest.javaknowledge.Concurrent.blockingqueue.ProductTest;

/**
 * blockqueue测试演示
 *
 */
public class Main {

	public static void main(String[] args) {
		//创建一个仓库，最大允许放入5个面包,采用array的实现类
		BlockingQueue<String> breadLib = new ArrayBlockingQueue<String>(5);
		
		//创建消费者
		CustomerTest customer = new CustomerTest(breadLib);
		//创建生产者
		ProductTest product = new ProductTest(breadLib);
		
		//创建线程队列
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(product);
		executor.submit(customer);
		
		executor.shutdown();
	}
	
}
