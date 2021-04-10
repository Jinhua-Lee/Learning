package cn.jvm.runtime.heap;

import lombok.SneakyThrows;
import org.springframework.util.StopWatch;

/**
 * 标量替换模拟<p>&emsp;
 * -Xms100M -Xmx100M -XX:+DoEscapeAnalysis -XX:+PrintGCDetails -XX:-EliminateAllocations
 *
 * @author Jinhua
 * @date 2021/4/10 23:22
 */
public class ScalarReplace {

    @SneakyThrows
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int objNum = 10_000_000;
        for (int i = 0; i < objNum; i++) {
            alloc();
        }
        // 1) 关闭标量替换：67ms
        // 2) 开启标量替换：4ms
        stopWatch.stop();
        System.out.println("花费的时间为： " + stopWatch.getTotalTimeMillis() + " ms");
    }

    public static void alloc() {
        User user = new User();
        user.id = 5;
        user.name = "某用户";
    }

    public static class User {
        public int id;
        public String name;
    }
}

/*
 class Customer {
     String name;
     int id;
     Account account;
 }

 class Account {
     double balance;
 }
*/
