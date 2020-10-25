/*
 * Copyright (c)    2020 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/com/jvm/runtime/stack/AnimalTest.java
 * LastModified:    2020/10/25 下午4:39
 */

package com.jvm.runtime.stack;

/**
 * 早期绑定和晚期绑定的例子
 * @author Jinhua
 * @date 2020/10/25 16:39
 */
public class AnimalTest {

    public void showAnimal(Animal animal) {
        // 表现为晚期绑定
        animal.eat();
    }

    public void showHunt(Huntable huntable) {
        // 表现为晚期绑定
        huntable.hunt();
    }

    public static void main(String[] args) {

    }
}

/**
 * 动物
 */
class Animal {

    /**
     * 进食方法
     */
    public void eat() {
        System.out.println("动物进食...");
    }
}

/**
 * 可捕猎的
 */
interface Huntable {

    /**
     * 捕猎方法
     */
    void hunt();
}

/**
 * 🐕
 */
class Dog extends Animal implements Huntable {

    @Override
    public void eat() {
        System.out.println("🐕吃骨头...");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，多管闲事！");
    }
}

/**
 * 🐱
 */
class Cat extends Animal implements Huntable {
    @Override
    public void eat() {
        System.out.println("🐱吃鱼...");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，天经地义...");
    }
}
