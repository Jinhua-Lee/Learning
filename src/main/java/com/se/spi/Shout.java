package com.se.spi;

import java.util.ServiceLoader;

/**
 * 嗷
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 下午2:59
 */
public interface Shout {

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

    static void main(String[] args) {
        ServiceLoader<Shout> shoutLoader = ServiceLoader.load(Shout.class);
        shoutLoader.forEach(Shout::shout);
    }
}


