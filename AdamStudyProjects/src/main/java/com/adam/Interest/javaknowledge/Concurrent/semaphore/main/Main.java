package com.adam.Interest.javaknowledge.Concurrent.semaphore.main;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.adam.Interest.javaknowledge.Concurrent.semaphore.SemaphoreText;

/**
 * 信号量演示
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//创建一个信号量，表示数据库最大连接为10
		Semaphore semaphore = new Semaphore(10);
		//创建一个具有30个容量的线程池，用来表示有30个线程想要操作数据库
//		ExecutorService executor = Executors.newFixedThreadPool(30);
		ExecutorService executor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(30));
		//放入线程池
		for(int a = 0; a < 30; a++){
			SemaphoreText text = new SemaphoreText(semaphore, a);
			Thread thread = new Thread(text);
			executor.submit(thread);
		}
		executor.shutdown();
	}
	
	/**
	 * 总结：关于ThreadPoolExecutor   即线程池
	 * 内部参数：  第一个：线程池允许的活动线程数，即线程池的常驻线程，哪怕此时没有任务，该线程池也会有指定数字个数的线程存在
	 * 		       第二个：线程池允许的最大线程数，常常和后面的线程缓冲队列连用，即当活跃线程全部都在执行任务，并且缓冲队列也被沾满时，此时线程池就会打开新的线程来执行缓冲队列的任务，但是打开的新线程的数量加上活跃线程的数量不能大于这个最大线程数
	 * 		       第三个：允许缓冲队列等待的最大时间，如果缓冲队列等待时间超过了这个设置时间，则把所有缓冲队列的任务全部撤销
	 * 		       第四个：最大时间的单位，秒，时，天，月等
	 * 		       第五个：缓冲队列，默认为SynchronousQueue，这个队列的意思是会直接把进入这个队列的任务交给线程，如果此时最大线程已经满了，剩余的任务就会直接撤销
	 */
}
