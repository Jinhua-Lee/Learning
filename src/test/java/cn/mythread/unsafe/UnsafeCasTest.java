package cn.mythread.unsafe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

/**
 * 【unsafe】CAS操作测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/25 21:53
 */
@Slf4j
public class UnsafeCasTest {

    private static final Unsafe MY_UNSAFE = MyUnsafeHolder.getMyUnsafe();

    /**
     * Unsafe cas操作用到
     */
    @Getter
    @AllArgsConstructor
    private static class MyUnsafeUser {
        private String name;
        private int age;
    }

    @Test
    @DisplayName(value = "【unsafe】cas操作测试")
    public void test() throws NoSuchFieldException, InstantiationException {
        long nameOffset = MY_UNSAFE.objectFieldOffset(
                MyUnsafeUser.class.getDeclaredField("name")
        );
        long ageOffset = MY_UNSAFE.objectFieldOffset(
                MyUnsafeUser.class.getDeclaredField("age")
        );

        MyUnsafeUser unsafeUser = (MyUnsafeUser) MY_UNSAFE.allocateInstance(MyUnsafeUser.class);
        MY_UNSAFE.putObject(unsafeUser, nameOffset, "ljh");
        MY_UNSAFE.putInt(unsafeUser, ageOffset, 26);

        String replaceName = "lwk";
        int replaceAge = 23;
        // 1. 利用cas替换对象的两个字段
        MY_UNSAFE.compareAndSwapObject(unsafeUser, nameOffset, "ljh", replaceName);
        MY_UNSAFE.compareAndSwapInt(unsafeUser, ageOffset, 26, replaceAge);

        // 2. 预期：替换后的值
        Assertions.assertEquals(replaceName, unsafeUser.name);
        Assertions.assertEquals(replaceAge, unsafeUser.age);
    }
}
