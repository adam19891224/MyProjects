package com.adam.Interest.javaknowledge.Concurrent.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

import com.adam.Utils.Utils;

public class Exchanger1 implements Runnable {

	//创建交换器的声明
	private Exchanger<ArrayList<Integer>> exchanger = null;
	
	private List<Integer> list = Utils.getList();
	
	private CountDownLatch countDownLatch = null;
	private CountDownLatch countDownLatch2 = null;
	public Exchanger1(Exchanger<ArrayList<Integer>> exchanger, CountDownLatch countDownLatch, CountDownLatch countDownLatch2){
		this.exchanger = exchanger;
		list.add(100);
		list.add(101);
		list.add(102);
		list.add(103);
		list.add(104);
		list.add(105);
		list.add(106);
		list.add(107);
		list.add(108);
		list.add(109);
		this.countDownLatch = countDownLatch;
		this.countDownLatch2 = countDownLatch2;
	}
	
	public void showBefore(){
		System.out.println("exchanger1的List，交换前");
		for (Integer i : this.list) {
			System.out.println("exchanger1: " + i);
		}
	}
	
	public void showAfter(){
		System.out.println("exchanger1的List，交换后");
		for(Integer i : this.list){
			System.out.println("exchanger1: " + i);
		}
	}
	
	@Override
	public void run() {
		try {
			//调用交换器，打印
			showBefore();
			this.countDownLatch.countDown();
			this.list = this.exchanger.exchange((ArrayList<Integer>) this.list);
			showAfter();
			this.countDownLatch2.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
