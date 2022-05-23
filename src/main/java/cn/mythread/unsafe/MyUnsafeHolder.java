package cn.mythread.unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 【unsafe】持有类
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/23 23:59
 */
@Slf4j
public class MyUnsafeHolder {

    /**
     * unsafe，需要通过反射获取
     */
    private static Unsafe myUnsafe;
    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            myUnsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            log.error("创建unsafe失败");
        } finally {
            assert myUnsafe != null;
        }
    }

    public static Unsafe getMyUnsafe() {
        return myUnsafe;
    }
}
