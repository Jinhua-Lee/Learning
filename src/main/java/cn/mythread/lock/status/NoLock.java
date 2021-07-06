package cn.mythread.lock.status;

import org.openjdk.jol.info.ClassLayout;

/**
 * 无锁状态，对象头信息
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/5 15:21
 */
public class NoLock {
    private static final Object OBJECT = new Object();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(OBJECT).toPrintable());
        int hashCode = OBJECT.hashCode();
        System.out.println(ClassLayout.parseInstance(OBJECT).toPrintable());
    }
}