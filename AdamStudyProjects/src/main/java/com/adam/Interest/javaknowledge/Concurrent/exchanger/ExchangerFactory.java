package com.adam.Interest.javaknowledge.Concurrent.exchanger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * concurrent  数据交换线程
 * 这个线程的作用相信从名字中我们也已经看出来了，它的作用是让两个线程互相交互数据，注意一点，只能是两个，多了就没用了
 * 
 * 这里我们就做一个交换案例，a线程内部放1到10的数字，b线程放100到109的数组，然后两个线程交换
 * @author Administrator
 *
 */
public class ExchangerFactory {

	private static Exchanger1 exchanger1 = null;
	private static Exchanger2 exchanger2 = null;
	
	public static Exchanger1 getExchanger1(Exchanger<ArrayList<Integer>> exchanger, CountDownLatch downLatch, CountDownLatch downLatch2){
		if(exchanger1 == null){
			exchanger1 = new Exchanger1(exchanger, downLatch, downLatch2);
		}
		return exchanger1;
	}
	
	public static Exchanger2 getExchanger2(Exchanger<ArrayList<Integer>> exchanger, CountDownLatch downLatch, CountDownLatch downLatch2){
		if(exchanger2 == null){
			exchanger2 = new Exchanger2(exchanger, downLatch, downLatch2);
		}
		return exchanger2;
	}
}
