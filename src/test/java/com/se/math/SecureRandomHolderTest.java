package com.se.math;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

/**
 * 测试Random的并发问题
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/13 16:43
 */
@Slf4j
public class SecureRandomHolderTest {

    private final Random random = new Random();

    public static void main(String[] args) {
        SecureRandomHolderTest secureRandomHolderTest = new SecureRandomHolderTest();
        secureRandomHolderTest.testRandomThreadSecure();
    }

    @Test
    @DisplayName(value = "测试Random的并发安全")
    @SneakyThrows
    public void testRandomThreadSecure() {
        Thread random10 = new Thread(randomIntPrint(10), "random10");
        Thread random50 = new Thread(randomIntPrint(50), "random50");

        random10.start();
        random50.start();
        // TODO: 2022/5/13 未完成
    }

    @Test
    @DisplayName(value = "测试SecureRandom的并发安全")
    @SneakyThrows
    public void testSecureRandomThreadSecure() {
        Thread secureRandom10 = new Thread(randomIntPrint(10), "secureRandom10");
        Thread secureRandom50 = new Thread(randomIntPrint(50), "secureRandom50");

        secureRandom10.start();
        secureRandom50.start();
        // TODO: 2022/5/13 未完成
    }

    @SuppressWarnings("all")
    private Runnable randomIntPrint(int bound) {
        return () -> {
            for (int i = 0; i < 1_000; i++) {
                int rValue = this.random.nextInt(bound);
                if (rValue >= bound) {
                    throw new RuntimeException("[random error] bound = " + bound + ", value = " + rValue);
                } else {
                    log.info("[random] bound = {}, value = {}", bound, rValue);
                }
            }
        };
    }

    @SuppressWarnings("all")
    private Runnable secureRandomIntPrint(int bound) {
        return () -> {
            for (int i = 0; i < 1_000; i++) {
                int srValue = SecureRandomHolder.secureRandom.nextInt(bound);
                if (srValue >= bound) {
                    throw new RuntimeException("[random error] bound = " + bound + ", value = " + srValue);
                } else {
                    log.info("[random] bound = {}, value = {}", bound, srValue);
                }
            }
        };
    }
}