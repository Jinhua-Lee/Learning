package com.thread;

public class MovieTicketThread extends Thread {
	private static int number = 100;
	
	public void run() {
		while(true) {
			synchronized (this) {
				if (number >= 0) {
					System.out.println(Thread.currentThread().getName() + " 售票中，还剩： " + (number--) + " 张票");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		MovieTicketThread mt1 = new MovieTicketThread();
		MovieTicketThread mt2 = new MovieTicketThread();
		MovieTicketThread mt3 = new MovieTicketThread();
		
		mt1.setName("窗口1");
		mt2.setName("窗口2");
		mt3.setName("窗口3");

		mt1.start();
		mt2.start();
		mt3.start();
	}
}
