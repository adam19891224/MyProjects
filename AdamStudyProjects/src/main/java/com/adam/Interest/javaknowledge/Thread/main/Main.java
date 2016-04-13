package com.adam.Interest.javaknowledge.Thread.main;

import com.adam.Interest.javaknowledge.Thread.ticketsystem.TicketWindows;

public class Main {

	public static void main(String[] args) {
		ticketTest();
	}
	
	
	private static void ticketTest(){
//		Thread t1 = new Thread(new TicketWindows());
//		Thread t2 = new Thread(new TicketWindows());
		TicketWindows tw = new TicketWindows();
		
		Thread t1 = new Thread(tw, "一");
		Thread t2 = new Thread(tw, "二");
		
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tw.flag = false;
		t2.start();
	}
}
