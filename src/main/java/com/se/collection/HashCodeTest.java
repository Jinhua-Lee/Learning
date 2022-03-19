package com.se.collection;

/**
 * Todo
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/17 18:00
 */
public class HashCodeTest {

    public static void main(String[] args) {

        int num = 100;
        int capacity = 16;

        System.out.println("start");
        for (int i = 0; i < num; i++) {
            if (i % capacity != ((capacity - 1) & i)) {
                throw new IllegalArgumentException("i = " + i);
            }
        }
        System.out.println("finished");
    }
}
