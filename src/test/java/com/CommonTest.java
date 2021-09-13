package com;

import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/9/11 16:06
 */
public class CommonTest {

    @Test
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
}
