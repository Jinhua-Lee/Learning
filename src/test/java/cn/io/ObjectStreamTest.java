package cn.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 【对象流】测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/6 23:22
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ObjectStreamTest {

    private static final String objFilePath = "D:/io_test/object_test.txt";
    private static final File objFile = new File(objFilePath);

    private static ObjectMapper mapper;

    private static final StudentSerial LJH = new StudentSerial("ljh", 25);
    private static final StudentSerial LWK = new StudentSerial("lwk", 22);
    private static final StudentReplaceSerial LJH_REPLACE = new StudentReplaceSerial("ljh", 25);

    @BeforeAll
    @AfterAll
    @SneakyThrows
    @DisplayName(value = "初始化系统文件状态")
    @SuppressWarnings("all")
    public static void beforeAll() {

        if (!objFile.exists() ||
                (objFile.exists()
                        && objFile.delete()
                        && objFile.createNewFile())) {
        }

        mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试对象的简单out与in")
    @SneakyThrows
    public void testSimpleOutAndIn() {
        // 1. 写出
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(objFile));

        log.info("[simple out]第一个对象：\n{}", mapper.writeValueAsString(LJH));
        objOutStream.writeObject(LJH);

        log.info("[simple out]第二个对象：\n{}", mapper.writeValueAsString(LWK));
        objOutStream.writeObject(LWK);

        objOutStream.flush();
        objOutStream.close();

        // 2. 读入
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(objFile));

        StudentSerial readLjh = (StudentSerial) objInStream.readObject();
        StudentSerial readLwk = (StudentSerial) objInStream.readObject();
        objInStream.close();

        // 3. 验证：
        // 1) 无transient字段信息
        // 2) 其余字段正确还原
        log.info("[simple in]第一个对象：\n{}", mapper.writeValueAsString(readLjh));
        log.info("[simple in]第二个对象：\n{}", mapper.writeValueAsString(readLwk));
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试重复写出的对象的相等性")
    @SneakyThrows
    public void testWriteSameObj() {
        // 1. 写出
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(objFile));

        log.info("[duplicated out]第一个对象：\n{}", mapper.writeValueAsString(LJH));
        objOutStream.writeObject(LJH);

        log.info("[duplicated out twice]写同一个对象：\n{}", mapper.writeValueAsString(LJH));
        objOutStream.writeObject(LJH);
        objOutStream.flush();
        objOutStream.close();

        // 2. 读入
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(objFile));

        StudentSerial readLjh = (StudentSerial) objInStream.readObject();
        StudentSerial readLjh2 = (StudentSerial) objInStream.readObject();
        objInStream.close();

        log.info("[duplicated in]读第一个对象：\n{}", mapper.writeValueAsString(readLjh));
        log.info("[[duplicated in]读第重复写的第一个对象：\n{}", mapper.writeValueAsString(readLjh));

        // 同一个对象写两次，读出来也相等
        Assertions.assertEquals(readLjh, readLjh2);
    }

    @Test
    @Order(value = 3)
    @DisplayName(value = "测试replace对象的out和in")
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void testReplaceObjOutAndIn() {
        // 1. 写出
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(objFile));

        log.info("out第一个替换对象：\n{}", mapper.writeValueAsString(LJH_REPLACE));

        objOutStream.writeObject(LJH_REPLACE);
        objOutStream.flush();
        objOutStream.close();

        // 2. 读入
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(objFile));

        // 读上来的是数组
        List<Object> objList = (List<Object>) objInStream.readObject();
        objInStream.close();

        // 3. 校验
        Assertions.assertEquals(2, objList.size());

        Assertions.assertEquals(String.class, objList.get(0).getClass());
        Assertions.assertEquals("ljh", objList.get(0));

        Assertions.assertEquals(Integer.class, objList.get(1).getClass());
        Assertions.assertEquals(25, objList.get(1));
    }

    @AfterAll
    @DisplayName(value = "删除文件")
    public static void afterAll() {
        if (objFile.exists() && objFile.delete()) {
            log.info("[delete file] success!");
        }
    }

    @Getter
    private static class StudentSerial implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 静态统计变量
         */
        private static int count = 0;
        protected String name;
        protected int age;

        /**
         * transient关键字标记的属性不进行序列化
         */
        private final transient int transFlag;

        private StudentSerial() {
            transFlag = -1;
            count++;
        }

        public StudentSerial(String name, int age) {
            this();
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 替换对象的输出和输入
     */
    @Getter
    @AllArgsConstructor
    private static final class StudentReplaceSerial extends StudentSerial {

        public StudentReplaceSerial(String name, int age) {
            super(name, age);
        }

        /**
         * 内部机制，供外调用者完全自定义序列化内容
         *
         * @return 真正要序列化的对象
         */
        @Serial
        private Object writeReplace() {
            List<Object> serialList = new ArrayList<>();
            serialList.add(name);
            serialList.add(age);
            return serialList;
        }
    }
}
