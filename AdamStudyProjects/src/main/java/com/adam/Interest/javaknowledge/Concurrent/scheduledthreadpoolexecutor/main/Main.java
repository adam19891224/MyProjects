package com.adam.Interest.javaknowledge.Concurrent.scheduledthreadpoolexecutor.main;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.adam.Interest.javaknowledge.Concurrent.scheduledthreadpoolexecutor.TimerText1;
import com.adam.Interest.javaknowledge.Concurrent.scheduledthreadpoolexecutor.TimerText2;

/**
 * 新版定时器演示
 *
 */
public class Main {

	public static void main(String[] args) {
		//创建定时线程池
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		TimerText1 text1 = new TimerText1();
		Thread thread = new Thread(text1);
		TimerText2 text2 = new TimerText2();
		Thread thread2 = new Thread(text2);
		executor.scheduleAtFixedRate(thread, 0, 1000, TimeUnit.MILLISECONDS);
		executor.scheduleAtFixedRate(thread2, 2000, 5000, TimeUnit.MILLISECONDS);
		/*
		 * 其中参数意义：第一个是执行的线程，
		 * 			第二个是从现在开始，几秒之后执行，例如第一个是0，即从现在开始，就立刻执行线程thread，第二是2000，即从现在开始，2秒后执行thread2
		 * 			第三个是间隔时间，即当线程执行之后，每隔多久再次执行
		 * 			第四个是数字的单位，可以设置秒，天，月等
		 * 所以综上:
		 * 第一个可以这样翻译：当程序运行到这一行之后，立刻开始执行thread线程，并且以后每隔1秒执行一次thread
		 * 第二个可以这样翻译：当程序运行到这一行之后，隔2秒，开始执行thread2线程，并且以后每隔5秒执行一次thread2
		 * 
		 */
		
		/**
		 * 这里注意一下，我们用的是scheduleAtFixedRate，而除此之外，还有个ScheduleWithFixedDelay
		 * scheduleAtFixedRate上面我们已经用过了，它的意义就是基于固定的时间，即我们设置间隔多少秒，它就每隔多少秒执行一次
		 * 而ScheduleWithFixedDelay不是固定的，它取决于每次执行任务的时间长短来决定间隔
		 */
		
	}
	
}
