package cn.mythread.demo.delivery;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 外卖送餐并统计时间
 *
 * @author Jinhua
 * @date 2020年8月15日 18点32分
 */
public class Delivery implements Callable<String> {

    /**
     * 送餐方法
     *
     * @return 送到时间信息
     * @throws Exception 异常情况
     */
    @Override
    public String call() throws Exception {
        // 送餐与送到时间间隔10秒内
        Thread.sleep(new Random().nextInt(10000));
        System.out.println(Thread.currentThread().getName() + "：您的外卖已送达");
        return Thread.currentThread().getName() + " 送达时间：" + LocalDateTime.now() + "\n";
    }

    /**
     * 1. Callable 作为参数传递给 FutureTask
     * 2. FutureTask 再作为参数传递给 Thread（类似 Runnable），然后就可以在子线程执行
     *
     * @param args 主函数入参
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        List<FutureTask<String>> futureTasks = new ArrayList<>(4);

        final int deliverySize = 4;
        // 4位送餐员
        for (int i = 0; i < deliverySize; i++) {
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