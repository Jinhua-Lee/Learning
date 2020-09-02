package com.thread;

public class MovieTicketRun implements Runnable {
	private static int number = 100;

	public void sell() {
		while(true) {
			synchronized (this) {
				if (number >= 0) {
					System.out.println(Thread.currentThread().getName() + " 售票中，还剩： " + (number--) + " 张票");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void run() {
		sell();
	}
	
	public static void main(String[] args) {
		MovieTicketRun mt = new MovieTicketRun();
		
		Thread t1 = new Thread(mt, "窗口1");
		Thread t2 = new Thread(mt, "窗口2");
		Thread t3 = new Thread(mt, "窗口3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}
