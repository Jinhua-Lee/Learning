package cn.mythread.unsafe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import sun.misc.Unsafe;

/**
 * 【unsafe】内存测试
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/24 18:13
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UnsafeMemoryTest {

    private static final Unsafe MY_UNSAFE = MyUnsafeHolder.getMyUnsafe();

    @Test
    @DisplayName(value = "测试【内存分配、重分配、释放】")
    public void testMemoryAllocate() {
        // 1. 分配1byte内存，等价于C语言malloc
        long address = MY_UNSAFE.allocateMemory(1L);
        MY_UNSAFE.putByte(address, (byte) 1);
        log.info("[unsafe malloc] address = {}, content = {}", address, MY_UNSAFE.getByte(address));

        // 2. 重新分配，等价于C语言realloc
        long newAddress = MY_UNSAFE.reallocateMemory(address, 8);
        log.info("[unsafe realloc] newAddress = {}, content = {}", newAddress, MY_UNSAFE.getLong(newAddress));

        // 3. 释放地址，等价于C语言free
        MY_UNSAFE.freeMemory(newAddress);
    }

    @Test
    @DisplayName(value = "测试【从一个long内容读取两个int内容】")
    public void testGetIntFromLong() {
        // 1. 分配一个字节的内存
        long address = MY_UNSAFE.allocateMemory(8L);

        // 2. 放置一个long类型的内容，调试内容是：00_00_01_80_dd_0c_1c_00
        MY_UNSAFE.putLong(address, 1652976000000L);
        log.info("[get int from long] put a long value, its hex value str = {}",
                Long.toHexString(MY_UNSAFE.getLong(address))
        );

        // 3. 读取。证实了unsafe的put方法存放，默认是小端模式（低地址存低字节，高地址存高字节）
        //      3.1 读取低地址4字节int，调试内容是：dd_0c_1c_00
        int lowInt = MY_UNSAFE.getInt(address);
        log.info("[get int from long] low 4 bytes, the int value = {}, its hex value str = {}",
                lowInt,
                Integer.toHexString(lowInt)
        );

        //      3.2 读取后4字节int，调试内容是：00_00_01_80
        int highInt = MY_UNSAFE.getInt(address + 4);
        log.info("[get int from long] high 4 bytes, the int value = {}, its hex value str = {}",
                highInt,
                Integer.toHexString(highInt)
        );
    }

    @Test
    @DisplayName(value = "测试【从两个int内容，读取一个long内容】")
    public void testPutIntToLong() {
        // 1. 分配一个字节的内存
        long address = MY_UNSAFE.allocateMemory(8L);

        // 2. 放置两个int类型的内容，低地址字节是00_01_e2_40，高地址字节是00_09_fb_f1
        MY_UNSAFE.putInt(address, 123456);
        MY_UNSAFE.putInt(address + 4, 654321);

        int firstInt = MY_UNSAFE.getInt(address);
        int secondInt = MY_UNSAFE.getInt(address + 4);
        log.info("[put int to long] first 4 bytes, the int value = {}, its hex value str = {}",
                firstInt,
                Integer.toHexString(firstInt)
        );
        log.info("[put int to long] second 4 bytes, the int value = {}, its hex value str = {}",
                secondInt,
                Integer.toHexString(secondInt)
        );

        // 3. 读取出一个long值
        long aLong = MY_UNSAFE.getLong(address);
        log.info("[put int to long] the long value = {}, its hex value str = {}",
                aLong,
                Long.toHexString(aLong)
        );
    }
}
