package cn.mythread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程相关测试
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/6/11 9:49
 */
public class ThreadTest {

    private static final ExecutorService EXECUTOR_SERVICE;

    static {
        EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);
    }

    @Test
    public void testExecute() {

    }

    @Test
    public void testSubmit() {

    }
}
