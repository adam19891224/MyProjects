package com.adam.Interest.javaknowledge.Concurrent.exchanger.main;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.adam.Interest.javaknowledge.Concurrent.exchanger.Exchanger1;
import com.adam.Interest.javaknowledge.Concurrent.exchanger.Exchanger2;
import com.adam.Interest.javaknowledge.Concurrent.exchanger.ExchangerFactory;

/**
 * 交换器演示
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//创建交换器
		Exchanger<ArrayList<Integer>> exchanger = new Exchanger<ArrayList<Integer>>();
		
		CountDownLatch frist = new CountDownLatch(1);
		CountDownLatch second = new CountDownLatch(1);
		
		Exchanger1 exchanger1 = ExchangerFactory.getExchanger1(exchanger, frist, second);
		
		Exchanger2 exchanger2 = ExchangerFactory.getExchanger2(exchanger, frist, second);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(exchanger1);
		executor.submit(exchanger2);
		
		executor.shutdown();
		
	}
	
}
