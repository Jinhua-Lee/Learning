package com.thread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest implements Callable {
	/**
	 * 实现 Callable 接口
	 */
	public String call() throws Exception {
		Thread.sleep(new Random().nextInt(5000));
		System.out.println(Thread.currentThread().getName() + "：您的外卖已送达");
		return Thread.currentThread().getName() + " 送达时间：" + LocalDateTime.now() + "\n";
	}

	/**
	 * Callable 作为参数传递给 FutureTask，FutureTask 再作为参数传递给 Thread（类似 Runnable），然后就可以在子线程执行
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		List<FutureTask<String>> futureTasks = new ArrayList<>(4);
		for (int i = 0; i < 4; i++) {
			CallableTest callable = new CallableTest();
			FutureTask<String> futureTask = new FutureTask<>(callable);
			futureTasks.add(futureTask);

			Thread thread = new Thread(futureTask, "送餐员 " + i);
			thread.start();
		}

		StringBuilder results = new StringBuilder();
		futureTasks.forEach(futureTask -> {
			try {
				//获取线程返回结果，没返回就会阻塞
				results.append(futureTask.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
		System.out.println(System.currentTimeMillis() + " 得到结果：\n" + results);
	}
}