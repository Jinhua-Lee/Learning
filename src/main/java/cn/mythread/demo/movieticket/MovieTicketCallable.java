package cn.mythread.demo.movieticket;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 电影院售票模拟，Callable 接口实现
 *
 * @author Jinhua
 * @date 2020/8/15 17:56
 */
@Slf4j
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
        StringBuilder sb = new StringBuilder();
        synchronized (this) {
            while (TICKET_NUM > 0) {
                String executeResult = Thread.currentThread().getName() + " 正在售票，剩余 " + (--TICKET_NUM) + " 张票";
                sb.append(executeResult).append(System.lineSeparator());
                System.out.println(executeResult);
                TimeUnit.MILLISECONDS.sleep(100L);
            }
        }
        return sb.toString();
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {


        List<FutureTask<String>> futureTasks = new ArrayList<>(MovieTicketCallable.WINDOW_NUM);

        for (int i = 0; i < MovieTicketCallable.WINDOW_NUM; i++) {
            FutureTask<String> futureTask = new FutureTask<>(new MovieTicketCallable());
            futureTasks.add(futureTask);
            Thread thread = new Thread(futureTask, "窗口" + (i + 1));
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
        log.info("{}", executeResult);
    }
}
