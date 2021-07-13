package cn.mythread.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/13 18:44
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        // 传入的是类的类型
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

        User user = new User("Java", 22);
        // 输出22
        System.out.println(a.getAndIncrement(user));
        // 输出23
        System.out.println(a.get(user));
    }
}

@Data
@AllArgsConstructor
class User {
    private String name;
    public volatile int age;
}
