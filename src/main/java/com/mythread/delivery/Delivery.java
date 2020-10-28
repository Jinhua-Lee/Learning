/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mythread/delivery/Delivery.java
 * LastModified:    2020/8/15 下午6:32
 */

package com.mythread.delivery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 外卖送餐并统计时间
 * @author Jinhua
 * @date 2020年8月15日 18点32分
 */
public class Delivery implements Callable<String> {
	/**
	 * 实现 Callable 接口
	 */
	@Override
	public String call() throws Exception {
		Thread.sleep(new Random().nextInt(10000));
		System.out.println(Thread.currentThread().getName() + "：您的外卖已送达");
		return Thread.currentThread().getName() + " 送达时间：" + LocalDateTime.now() + "\n";
	}

	/**
	 * Callable 作为参数传递给 FutureTask，FutureTask 再作为参数传递给 Thread（类似 Runnable），然后就可以在子线程执行
	 *
	 * @param args 主函数入参
	 */
	public static void main(String[] args) {
		List<FutureTask<String>> futureTasks = new ArrayList<>(4);
		for (int i = 0; i < 4; i++) {
			Delivery callable = new Delivery();
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
		System.out.println(LocalDateTime.now() + " 得到结果：\n" + results);
	}
}