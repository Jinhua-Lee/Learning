package com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Test
    @DisplayName(value = "测试【引用类型】数组复制，结果是仅复制了一层引用")
    public void testSystemArrayCopyReference() {

        @Getter
        @AllArgsConstructor
        class User {
            String name;
            Integer age;
        }

        User[] srcUserArr = new User[3];
        srcUserArr[0] = new User("ljh", 26);
        srcUserArr[1] = new User("lwk", 23);

        User[] dstUserArr = new User[3];
        System.arraycopy(srcUserArr, 0, dstUserArr, 1, 2);

        // 预测，System的数组复制也仅仅是复制了一层引用
        String undefined = "undefined";
        dstUserArr[1].name = undefined;
        Assertions.assertEquals(undefined, srcUserArr[0].name);
    }

    @Test
    @DisplayName(value = "测试List的索引遍历是否会引起fail-fast")
    public void testListSize() {
        List<Long> longList = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L));
        for (int i = 0; i < longList.size(); i++) {
            Long aLong = longList.get(i);
            if (aLong % 3 == 0) {
                longList.remove(aLong);
            }
        }
    }

    @Test
    @DisplayName(value = "测试double丢失精度")
    public void testDecimalOfDouble() {
        double d1 = 0.07;
        double d2 = 0.02;

        double sub = d1 + d2;
        System.out.println(sub);
        Assertions.assertEquals(0.09d, sub);
    }

    @Test
    @DisplayName(value = "测试除法和求余数")
    public void testDivide() {
        int itemNumber = 1;
        int totalNumber = 9;
        int totalUsage = 80;

        int remainder = itemNumber * totalUsage % totalNumber;

        int num = (itemNumber * totalUsage - remainder) / 9;

        System.out.println("remainder = " + remainder);
        System.out.println("num = " + num);

    }

    @Test
    @DisplayName(value = "系统属性和环境")
    public void testSystem() {
        // user.dir是当前项目路径
        System.out.println("System.getProperty(\"user.dir\") = " + System.getProperty("user.dir"));
    }

    @Test
    @DisplayName(value = "测试双等号比较到Null对象")
    public void testEqualNull() {
        byte b = 1;
        Byte by = null;

        System.out.println("b == by = " + (b == by));
    }

    @Test
    @DisplayName(value = "测试MapToInt方法对空元素的处理")
    public void testMapToInt() {
        @Getter
        @AllArgsConstructor
        class A {
            Integer a;
        }
        List<A> as = new ArrayList<>();

        as.add(new A(1));
        as.add(null);

        // 聚合操作必须过滤null元素
        int maxA = as.stream().filter(Objects::nonNull).mapToInt(A::getA).max().orElse(0);
        System.out.println("maxA = " + maxA);
    }

    @Test
    @DisplayName(value = "测试null加上运算符")
    public void testNullOp() {
        Boolean aBool = null;
        if (true && aBool) {
            System.out.println("true && null");
        }
    }

    @Test
    @DisplayName(value = "测试聚合")
    public void testAggregation() {
        @Getter
        @AllArgsConstructor
        class A {
            Integer a;
        }
        List<A> as = new ArrayList<>();
        int i = as.parallelStream().mapToInt(A::getA).max().orElse(-1);
        System.out.println("i = " + i);
    }
}
