package com.adam.Interest.javaknowledge.Concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * concurrent 循环计数器案例
 * 这个计数器与前面介绍的countDownLatch不用，这个计数器的作用是让所有有这个计数器的对象同时被接管，并且相互约束，仅当所有线程全部执行完毕之后，才会释放
 * 前面我们用countDownLatch时发现一个问题，有些线程并没有执行完毕自己的打印语句，但是count的计数器却变成0了，那么此时就有可能出现打印语句穿插在helloworld语句中的情况
 * 而cyclicbarrier就不会出现这种情况,它没有数字的概念，也没有countDown,它只有await()挂起方法，当所有线程执行完毕之后，它会自动释放挂起锁,我们还是采用前面的例子，
 * 10个线程，每个线程打印5句话，然后所有线程准备完毕后，打印执行完毕
 * @author adam
 * 2015.5.28
 */
public class CyclicBarrierText implements Runnable {
	
	private CyclicBarrier cyclicBarrier = null;
	private CountDownLatch downLatch = null;
	private int num;
	
	public CyclicBarrierText(CyclicBarrier b, CountDownLatch latch, int n){
		this.cyclicBarrier = b;
		this.downLatch = latch;
		this.num = n;
	}
	
	private void show(){
		try {
			System.out.println("线程" + this.num + "  准备完毕");
			//挂起线程
			this.cyclicBarrier.await();
			for(int a = 0; a < 5; a++){
				System.out.println("线程 " + this.num + "号：   打印语句hello world");
			}
			this.downLatch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.show();
	}

}
