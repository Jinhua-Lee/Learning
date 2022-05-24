package cn.mythread.unsafe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import sun.misc.Unsafe;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * 【Unsafe】对象操作测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/24 0:02
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UnsafeObjectTest {

    private static final Unsafe MY_UNSAFE = MyUnsafeHolder.getMyUnsafe();

    /**
     * Unsafe对象操作用到
     */
    @Getter
    @AllArgsConstructor
    private static class MyUnsafeUser {
        private String name;
        private int age;
    }

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

    private static MyUnsafeUser myUnsafeUser;

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试【无需构造参数，进行对象实例化】")
    @SneakyThrows
    public void testInstantiationWithoutConstructor() {
        // 1. 无需构造，进行对象实例化
        myUnsafeUser = (MyUnsafeUser) MY_UNSAFE.allocateInstance(MyUnsafeUser.class);
        // 预测基本类型为默认值，引用类型为null
        Assertions.assertNotNull(myUnsafeUser);
        Assertions.assertNull(myUnsafeUser.getName());
        Assertions.assertEquals(0, myUnsafeUser.age);
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试【对象字段读写】")
    @SneakyThrows
    public void testFieldReadAndWrite() {
        // 2. 对象偏移及读写字段
        long nameFieldOffset = MY_UNSAFE.objectFieldOffset(MyUnsafeUser.class.getDeclaredField("name"));
        long ageFieldOffset = MY_UNSAFE.objectFieldOffset(MyUnsafeUser.class.getDeclaredField("age"));
        log.info("name字段的偏移值为{}, age字段的偏移值为{}", nameFieldOffset, ageFieldOffset);

        // 跳过setter直接写，注意基本类型和引用类型的API
        String setName = "ljh";
        int setAge = 26;
        MY_UNSAFE.putObject(myUnsafeUser, nameFieldOffset, setName);
        MY_UNSAFE.putInt(myUnsafeUser, ageFieldOffset, setAge);
        Assertions.assertEquals(setName, myUnsafeUser.name);
        Assertions.assertEquals(setAge, myUnsafeUser.age);
    }

    @Test
    @Order(value = 3)
    @DisplayName(value = "测试【运行时读取文件流对象class，创建对象】")
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void testObjectOperations() {
        // 3. 运行时读取类型，创建对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CLASS_FILE));
        // 加载类
        Object readObj = ois.readObject();
        Class<MyUnsafeUser> myUnsafeUserClazz = (Class<MyUnsafeUser>) readObj;
        ois.close();
        // 创建对象
        Constructor<MyUnsafeUser> stringIntCtr = myUnsafeUserClazz.getDeclaredConstructor(String.class, int.class);
        MyUnsafeUser reflectUser = stringIntCtr.newInstance("lwk", 23);
        Assertions.assertNotNull(reflectUser);
        Assertions.assertEquals("lwk", reflectUser.name);
        Assertions.assertEquals(23, reflectUser.age);
    }
}
