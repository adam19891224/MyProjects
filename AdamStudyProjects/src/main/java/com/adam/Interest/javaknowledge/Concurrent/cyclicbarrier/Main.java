package com.adam.Interest.javaknowledge.Concurrent.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		//创建循环计数器
		CyclicBarrier barrier = new CyclicBarrier(10);
		CountDownLatch downLatch = new CountDownLatch(10);
		//线程池
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int a = 0; a < 10; a++){
			CyclicBarrierText barrierText = new CyclicBarrierText(barrier, downLatch, a);
			Thread thread = new Thread(barrierText);
			executor.submit(thread);
		}
		try {
			downLatch.await();
			System.out.println("所有线程执行完毕");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
	
}
