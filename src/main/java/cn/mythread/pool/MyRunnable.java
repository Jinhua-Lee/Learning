package cn.mythread.pool;

import lombok.SneakyThrows;

import java.time.LocalDateTime;

/**
 * 简单Runnable类实现，需要5s完成该任务
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/6/27 23:39
 */
public class MyRunnable implements Runnable {

    private final String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    @SneakyThrows
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + LocalDateTime.now());
        Thread.sleep(5_000);
        System.out.println(Thread.currentThread().getName() + " End. Time = " + LocalDateTime.now());
    }

    @Override
    public String toString() {
        return this.command;
    }
}
