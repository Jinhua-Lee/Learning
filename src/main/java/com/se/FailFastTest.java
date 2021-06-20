package com.se;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 快速失败机制测试类
 *
 * @author Jinhua
 * @date 2021/6/19 0:44
 * @version 1.0
 */
public class FailFastTest {

    @Test
    @SuppressWarnings("all")
    public void testFail() {
        List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        for (Integer anInt : ints) {
            if (anInt % 3 == 0) {
                // 在用增强for循环遍历的时候，修改集合结构，会抛出ConcurrentModificationException
                ints.remove(anInt);
            }
        }
        ints.forEach(System.out::println);
    }

    @Test
    @SuppressWarnings("all")
    public void testFail2() {
        // 两种实现交替打开，以观察运行结果
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
//        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if (next % 3 == 0) {
                list.remove(next);
            }
        }
        list.forEach(System.out::println);
    }
}
