package com.adam.Interest.javaknowledge.Concurrent.countdowmlatch;

import java.util.concurrent.CountDownLatch;

/**
 * current countDownLatch计数器教程
 * 该类是一个多线程辅助类，作用是让其他线程挂起，然后等待该计数器计数完毕后，一起释放
 * 我们一般会这样做： CountDownLatch c = new CountDownLatch(1);//后面的1表示计数次数
 * 然后把它传入一个线程中（实现runable或者继承thread）
 * 然后在线程中调用它的await()方法，表示挂起等待： c.await();当调用这个方法之后，它后面的所有代码都暂时不会运行
 * 然后在main方法中，这样使用：c.countDown(); 让计数器传入的数自减1，
 * 由于此时我们是把c传入的线程，所以线程中的c与main方法中的c就是同一个计数器
 * 当计数器数字减为0时，就会释放所有c.await()方法，然后所有调用了c.await()的方法都会成功释放，就开始执行后面的代码了
 * 
 * 这里注意，我们New计数器的时候，传的是1，如果此时我们传一个大于1的数，当我们在main中调用countDown时，它就不会自减为0，所以这样这个c.await()就永远得不到释放了
 * 所以当我们使用这个计数器的时候，一定要注意定义好数字
 * 
 * 这个类可以帮我们实现一个类似这样的功能：
 * 此时我们开启10个线程，每个线程打印一句话，共打印5次，那么一共就是50句，由于线程是一一执行的，所以肯定会有先后，不过我们的需求是不用管它们的先后，我们只想在所有的10个线程都打印完后
 * 主程序在打印一句所有线程执行完毕的提示，此时就可以采用这种方式了。
 * 我们定义一个计数器，数字为10，同时创建10个线程，然后传入每一个线程之中，接着在后面调用计数器的await()挂起方法，
 * 在挂起方法之后，调用打印所有线程执行完毕的语句，然后在线程中，当每个线程打印完自己的5句话后，让这个计数器countDown，即自减1,
 * 由于我们把这个计数器传入了10个线程中，所以它们此时共享这一个计数器数字，所以当某一个线程运行完后，自减一，就会更改所有线程中计数器的数字，当10个线程都运行完毕后，
 * 这个数字就变成了0，然后await()挂起方法就释放，此时就执行后面的语句，我们后面的语句正好就是打印语句
 * 接下来我们就模拟这样一种情况，来看看计数器的使用方法
 * @author adam
 * 2015.5.28
 */
public class CountDownLatchThread implements Runnable {

	//这就是我们的计数器线程，它的作用就是打印5句话,由于要接收计数器，所以我们可以通过构造方法来传参
	//声明几个变量，一个是计数器变量，一个是自己的标识
	private CountDownLatch downLatch = null;
	private CountDownLatch startLatch = null;
	private int number = 0;
	//构造方法
	public CountDownLatchThread(CountDownLatch countDownLatch, CountDownLatch startLatch, int num){
		this.downLatch = countDownLatch;
		this.startLatch = startLatch;
		this.number = num;
	}
	
	//打印方法,根据需求，每个线程需要打印5次，这里就打印哪个线程打印了hellowordl
	private void show(){
		for(int a = 0; a < 5; a++){
			System.out.println("线程" + this.number + "号：  打印语句hello world");
		}
	}

	@Override
	public void run() {
		try {
			//调用准备完毕的语句
			System.out.println("线程" + this.number + "  准备完毕，等待startLatch发令");
			this.startLatch.countDown();
			this.startLatch.await();
			//调用show方法
			this.show();
			//然后让计数器自减1
			this.downLatch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
