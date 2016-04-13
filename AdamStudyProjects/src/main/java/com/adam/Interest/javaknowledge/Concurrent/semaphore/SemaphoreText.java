package com.adam.Interest.javaknowledge.Concurrent.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * concurrent 并发信号量演示
 * 这个信号量相当于一个信号灯，从字面上不是很容易理解，这里我们举个例子就能很轻松的了解到这个类的具体含义了
 * 假设此时有一段公路，最多只能允许20辆车在路上运行，而此时从一边开来了40辆车，那么这个信号灯此时就会给前20辆车展示绿灯，许可它们进入，当20辆车驶入后，此时信号灯变红，其他车由于没有
 * 得到许可，就排队等候，此时正好有5辆车驶出了另一边，那么此时信号灯又变绿，放行排载前面的5辆车，接着又变红
 * 这里的semaphore就代表了上面的信号灯，而它的acquire()方法就表示添加一个许可，即亮绿灯，而release()方法就表示释放这个许可，即例子中的车驶出了另一端，然后就把许可归还，此时其他线程
 * 就可以得到这个许可了
 * 
 * 这里我们就模拟这样一个操作，此时有30个线程需要操作数据库，但是此时数据库只有10个有效连接，所以没有拿到连接的线程就需要等待拿到连接的线程操作完后释放连接，再去获取
 * 
 * @author adam
 * 2015.5.28
 *
 */
public class SemaphoreText implements Runnable {

	//定义一个许可声明
	private Semaphore semaphore = null;
	private int num;
	//构造函数
	public SemaphoreText(Semaphore semaphore, int num){
		this.semaphore = semaphore;
		this.num = num;
	}
	
	//执行操作
	public void excute(){
		try {
			//首先需要获取许可，即连接
			this.semaphore.acquire();
			//然后操作数据库，这里我们就打印
			System.out.println("线程" + this.num + "  正在操作数据库....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//释放操作，释放链接
	public void cancle(){
		System.out.println("线程" + this.num + "  操作完毕，释放连接");
		this.semaphore.release();
	}

	@Override
	public void run() {
		try {
			this.excute();
			Thread.sleep(new Random().nextInt(5) * 1000);
			this.cancle();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
