package com.se.spi;

import java.util.ServiceLoader;

/**
 * SPI机制的接口，注意SPI内部类名字的写法
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 下午2:59
 */
public interface Shout {

    /**
     * 嗷
     */
    void shout();

    class Cat implements Shout {

        @Override
        public void shout() {
            System.out.println("喵～");
        }
    }

    class Dog implements Shout {

        @Override
        public void shout() {
            System.out.println("汪～");
        }
    }

    /**
     * main
     *
     * @param args 参数
     */
    static void main(String[] args) {
        ServiceLoader<Shout> shoutLoader = ServiceLoader.load(Shout.class);
        shoutLoader.forEach(Shout::shout);
    }
}


