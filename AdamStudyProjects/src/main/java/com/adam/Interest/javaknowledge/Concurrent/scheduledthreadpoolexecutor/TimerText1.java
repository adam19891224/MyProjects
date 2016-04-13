package com.adam.Interest.javaknowledge.Concurrent.scheduledthreadpoolexecutor;
/**
 * scheduledthreadpoolexecutor 即新的定时器线程类，原先我们都是使用的timer来做定时，现在就可以用这个类来定时， 并且如果某一个线程报错异常，也并不会影响另一个线程执行
 * @author adam
 * 2015.5.28
 */
public class TimerText1 implements Runnable {

	@Override
	public void run() {
		//这个线程就打印当前系统时间
		System.out.println("我是timer1");
	}

}
