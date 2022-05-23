package cn.mythread.unsafe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 【Unsafe】测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/23 22:08
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UnsafeTest {

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
        } finally {
            assert myUnsafe != null;
        }
    }

    /**
     * Unsafe对象操作用到
     */
    @Getter
    @AllArgsConstructor
    private static class MyUnsafeUser {
        private String name;
        private int age;
    }

    /* 数组类型的测试 */
    private final String[] strings = {"hi", "hello"};
    private final int strArrBaseOffset = myUnsafe.arrayBaseOffset(String[].class);
    private final int strArrIndexScale = myUnsafe.arrayIndexScale(String[].class);

    /* 类类型的测试 */
    private static final String CLASS_FILE_PATH = "D:/io_test/class_file/";
    private static final File CLASS_FILE = new File(CLASS_FILE_PATH, MyUnsafeUser.class.getSimpleName());

    @BeforeAll
    @DisplayName("写出class对象")
    @SneakyThrows
    @SuppressWarnings("all")
    public static void beforeAll() {

        if (!CLASS_FILE.exists() ||
                (CLASS_FILE.exists()
                        && CLASS_FILE.delete())) {
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CLASS_FILE));
        oos.writeObject(MyUnsafeUser.class);
        oos.close();
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试数组相关API")
    public void testArrayOperations() {
        log.info("string数组的基本偏移量是{}, 增量地址是{}", strArrBaseOffset, strArrIndexScale);

        // 1. 读数组的指定索引元素
        final int index = 3;
        String str = (String) myUnsafe.getObject(strings, strArrBaseOffset + (long) index * strArrIndexScale);
        log.info("数组的第{}个元素是{}", index + 1, str);
        Assertions.assertEquals(strings[index], str);

        // 2. 写数组的第i个元素
        String replace = "you!";
        myUnsafe.putObject(strings, strArrBaseOffset + (long) index * strArrIndexScale, replace);
        String reGet = (String) myUnsafe.getObject(strings, strArrBaseOffset + (long) index * strArrIndexScale);
        log.info("再次获取数组的第{}个元素是{}", index + 1, reGet);
        Assertions.assertEquals(replace, reGet);
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试对象相关API")
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void testObjectOperations() {

        // 1. 无需构造，进行对象实例化
        MyUnsafeUser myUnsafeUser = (MyUnsafeUser) myUnsafe.allocateInstance(MyUnsafeUser.class);
        // 预测基本类型为默认值，引用类型为null
        Assertions.assertNull(myUnsafeUser.getName());
        Assertions.assertEquals(0, myUnsafeUser.age);

        // 2. 对象偏移及读写字段
        long nameFieldOffset = myUnsafe.objectFieldOffset(MyUnsafeUser.class.getDeclaredField("name"));
        long ageFieldOffset = myUnsafe.objectFieldOffset(MyUnsafeUser.class.getDeclaredField("age"));
        log.info("name字段的偏移值为{}, age字段的偏移值为{}", nameFieldOffset, ageFieldOffset);

        // 跳过setter直接写，注意基本类型和引用类型的API
        String setName = "ljh";
        Integer setAge = 26;
        myUnsafe.putObject(myUnsafeUser, nameFieldOffset, setName);
        myUnsafe.putInt(myUnsafeUser, ageFieldOffset, setAge);
        Assertions.assertEquals(setName, myUnsafeUser.name);
        Assertions.assertEquals(setAge, myUnsafeUser.age);

        // 3. 运行时读取类型，创建对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CLASS_FILE));
        // 加载类
        Object readObj = ois.readObject();
        Class<MyUnsafeUser> myUnsafeUserClazz = (Class<MyUnsafeUser>) readObj;
        ois.close();
        // 创建对象
        Constructor<MyUnsafeUser> stringIntCtr = myUnsafeUserClazz.getDeclaredConstructor(String.class, int.class);
        stringIntCtr.newInstance("lwk", 25);
    }
}
