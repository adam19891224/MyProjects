package com.adam.Interest.javaknowledge.Thread.producerAcustomer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者---消费者模式演练
 * @author adam
 * 2015.8.26
 */
public class PandC {
	
	public static void main(String[] args) {
		//创建鸭子对象
		Duck d = new Duck();
		//生产者与消费者
		Producer p = new Producer(d);
		Customer c = new Customer(d);
		
		//线程
		Thread t0 = new Thread(p);
		Thread t1 = new Thread(p);
		
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);
		
		//启动
		t0.start();
		t1.start();
		t2.start();
		t3.start();
	}

}

//class Duck{
//	
//	private String name;
//	private int count = 1;
//	private boolean isOk = false;
//	
//	
//	//生产方法, 由于使用了wait与nofity，所以必须要同步代码锁
//	public synchronized void create(String name){
//		while(isOk){//isOk为true，表示此时生产者生产完毕，则此时需要冻结该线程
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		//没冻结，就进行生产
//		this.name = name + count;//生成烤鸭1，烤鸭2的句式
//		count++;//count自加
//		System.out.println(Thread.currentThread().getName() + " ..生产了.." + this.name);
//		//修改状态并唤醒对方线程
//		isOk = true;
//		this.notifyAll();
//	}
//	
//	//消费方法
//	public synchronized void buy(){
//		//判断，如果此时isOk为false，就进入if条件，让当前线程冻结，
//		//但是if条件进入的判断是为ture，此时isOk是false，怎么能保证当isOk为false时，进入if呢？
//		//我们用! isOk即可
//		while(!isOk){
//			try {
//				this.wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		//为了以示区别，我们在消费者中语句打印长一点
//		System.out.println(Thread.currentThread().getName() + ".......消费了......." + this.name);
//		//此时切换标识
//		isOk = false;
//		this.notifyAll();
//	}
//}


//JDK 5.0版本的鸭子类
class Duck{
	
	private String name;
	private int count = 1;
	private boolean isOk = false;
	
	//创建锁
	Lock duckLock = new ReentrantLock();
	
	//创建两组状态来分别管理生产者与消费者
	Condition proCons = duckLock.newCondition();
	Condition cusCons = duckLock.newCondition();
	
	public void create(String name){
		duckLock.lock();
		//用try  catch  finally
		try {
			//循环判断，防止唤醒后不执行判断的问题
			while(isOk){
				try {
					//调用生产者状态的冻结方法
					proCons.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			this.name = name + count;
			count++;
			System.out.println(Thread.currentThread().getName() + " ..生产了.." + this.name);
			isOk = true;
			//调用消费者状态的唤醒方法
			cusCons.signal();
			
		} catch (Exception e) {
		} finally{
			//关闭锁
			duckLock.unlock();
		}
	}
	
	//消费方法
	public synchronized void buy(){
		//开启锁
		duckLock.lock();
		try {
			while(!isOk){
				try {
					//调用消费者状态的冻结方法
					cusCons.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + ".......消费了......." + this.name);
			isOk = false;
			
			//调用生产者的唤醒方法
			proCons.signal();
			
		} catch (Exception e) {
		} finally{
			//关闭锁
			duckLock.unlock();
		}
	}
}




//生产者
class Producer implements Runnable{

	//构造方法传参，保证duck对象是唯一的
	private Duck d = null;
	
	public Producer(Duck d){
		this.d = d;
	}
	
	@Override
	public void run() {
		//循环执行
		while(true){
			d.create("烤鸭");
		}
	}
}

//消费者
class Customer implements Runnable{

	//构造方法传参，保证duck对象是唯一的
	private Duck d = null;
	
	public Customer(Duck d){
		this.d = d;
	}
	
	@Override
	public void run() {
		//循环执行
		while(true){
			d.buy();
		}
	}
}
