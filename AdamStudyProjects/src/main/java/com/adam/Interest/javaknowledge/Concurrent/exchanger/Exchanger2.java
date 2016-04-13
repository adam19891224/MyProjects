package com.adam.Interest.javaknowledge.Concurrent.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

import com.adam.Utils.Utils;

public class Exchanger2 implements Runnable {

	//创建交换器的声明
	private Exchanger<ArrayList<Integer>> exchanger = null;
	
	private List<Integer> list = Utils.getList();
	
	private CountDownLatch countDownLatch = null;
	
	private CountDownLatch countDownLatch2 = null;
	
	public Exchanger2(Exchanger<ArrayList<Integer>> exchanger, CountDownLatch countDownLatch, CountDownLatch countDownLatch2){
		this.exchanger = exchanger;
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		this.countDownLatch = countDownLatch;
		this.countDownLatch2 = countDownLatch2;
	}
	
	public void showBefore(){
		System.out.println("exchanger2的List，交换前");
		for (Integer i : this.list) {
			System.out.println("exchanger2: " + i);
		}
	}
	
	public void showAfter(){
		System.out.println("exchanger2的List，交换后");
		for(Integer i : this.list){
			System.out.println("exchanger2: " + i);
		}
	}
	
	@Override
	public void run() {
		try {
			this.countDownLatch.await();
			//调用交换器，打印
			showBefore();
			this.list = this.exchanger.exchange((ArrayList<Integer>) this.list);
			this.countDownLatch2.await();
			showAfter();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
