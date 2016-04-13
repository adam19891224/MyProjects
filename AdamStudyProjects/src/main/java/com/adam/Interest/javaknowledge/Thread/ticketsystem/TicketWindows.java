package com.adam.Interest.javaknowledge.Thread.ticketsystem;

public class TicketWindows implements Runnable {

	private static int ticket = 10;
	
	Object obj = new Object();
	
	public boolean flag = true;
	
	public MyLock lockA = new MyLock();
	public MyLock lockB = new MyLock();

	@Override
	public void run() {
		if(flag){
			while (true) {
				synchronized (lockA) {
					synchronized (lockB) {
						if(ticket > 0){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(Thread.currentThread().getName() + " 号窗口卖出    " + ticket-- + " 号票");
						}
						else
							break;
					}
				}
			}
		}else{
			while (true) {
				synchronized (lockB) {
					synchronized (lockA) {
						if(ticket > 0){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(Thread.currentThread().getName() + " 号窗口卖出    " + ticket-- + " 号票");
						}
						else
							break;
					}
				}
			}
		}
	}
	
	
	class MyLock{
		
	}
	
//	@Override
//	public void run() {
//		if(flag){
//			while (true) {
//				synchronized (new TicketWindows().getClass()) {
//					if(ticket > 0){
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						System.out.println(Thread.currentThread().getName() + " 号窗口卖出    " + ticket-- + " 号票");
//					}
//					else
//						break;
//				}
//			}
//		}else{
//			while (true) {
//				sail();
//			}
//		}
//	}
//	
//	public synchronized static void sail(){
//		if(ticket > 0){
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(Thread.currentThread().getName() + " 号窗口卖出    " + ticket-- + " 号票");
//		}
//		else
//			return;
//	}
}
