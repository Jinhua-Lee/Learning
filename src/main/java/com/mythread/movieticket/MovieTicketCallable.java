/*
 * Copyright (c)    2020/8/15 下午5:56.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/mythread/movieticket/MovieTicketCallable.java
 * LastModified:    2020/8/15 下午5:56
 */

package com.mythread.movieticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Jinhua
 * @description
 * @date: 2020/8/15 17:56
 */
public class MovieTicketCallable implements Callable<String> {

    /**
     * 票总数量
     */
    private static Integer TICKET_NUM = 100;

    /**
     * 售票窗总数量
     */
    private static final Integer WINDOW_NUM = 5;


    @Override
    public String call() throws InterruptedException {
        StringBuffer sb = new StringBuffer();
        synchronized (this) {
            while (TICKET_NUM > 0) {
                String executeResult = Thread.currentThread().getName() + " 正在售票，剩余 " + (--TICKET_NUM) + " 张票";
                sb.append(executeResult).append(System.lineSeparator());
                System.out.println(executeResult);
                Thread.sleep(100);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {


        List<FutureTask<String>> futureTasks = new ArrayList<>(MovieTicketCallable.WINDOW_NUM);

        for (int i = 0; i < MovieTicketCallable.WINDOW_NUM; i++) {
            FutureTask<String> futureTask = new FutureTask<>(new MovieTicketCallable());
            futureTasks.add(futureTask);
            Thread thread = new Thread(futureTask, "窗口" + ( i + 1));
            thread.start();
        }

        StringBuffer executeResult = new StringBuffer();

        futureTasks.forEach(futureTask -> {
            try {
                executeResult.append(futureTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
