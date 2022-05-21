package com;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 16:06
 */
public class CommonTest {

    @Test
    @DisplayName(value = "测试Integer缓存")
    @SneakyThrows
    public void testIntegerCache() {
        Class<?> cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);

        Integer[] newCache = (Integer[]) myCache.get(cache);
        // 直接通过反射，修改了cache中的值
        newCache[132] = newCache[133];

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b);
    }

    @Test
    @DisplayName(value = "测试位交换")
    public void testBitsSwap() {
        // 交换高低字节
        short before = 1;
        short shortReversed = Short.reverseBytes(before);
        int intReversed = Integer.reverseBytes(before);

        // [][...1] => [...1][]
        // AB两个区域，交换后变为BA
        System.out.println("shortReversed = " + shortReversed);
        Assertions.assertEquals(1 << 8, shortReversed);
        // [][][][...1] -> [][...1][][]
        // A,B,C,D四个字节区域，交换后变为D,C,B,A
        System.out.println("intReversed = " + intReversed);
        Assertions.assertEquals(1 << 24, intReversed);
    }
}
