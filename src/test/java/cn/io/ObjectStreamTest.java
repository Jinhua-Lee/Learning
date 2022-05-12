package cn.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.*;

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

    @BeforeAll
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
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
    @DisplayName(value = "测试对象输出")
    @SneakyThrows
    public void testOutputObj() {
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(objFile));

        StudentSerial ljh = new StudentSerial("ljh", 25);
        StudentSerial lwk = new StudentSerial("lwk", 22);
        log.info("out第一个对象：\n{}", mapper.writeValueAsString(ljh));
        log.info("out第二个对象：\n{}", mapper.writeValueAsString(lwk));

        objOutStream.writeObject(ljh);
        objOutStream.writeObject(lwk);
        objOutStream.flush();
        objOutStream.close();
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试对象输入")
    @SneakyThrows
    public void testInputObj() {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(objFile));

        StudentSerial ljh = (StudentSerial) objInStream.readObject();
        StudentSerial lwk = (StudentSerial) objInStream.readObject();
        objInStream.close();

        // 验证：
        // 1. 无transient字段信息
        // 2. 其余字段正确还原
        log.info("in第一个对象：\n{}", mapper.writeValueAsString(ljh));
        log.info("in第二个对象：\n{}", mapper.writeValueAsString(lwk));
    }

    @Getter
    private static final class StudentSerial implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        /**
         * 静态常量
         */
        private static int count = 0;
        private String name;
        private Integer age;

        /**
         * transient关键字标记的属性不进行序列化
         */
        private final transient int transFlag;

        private StudentSerial() {
            transFlag = -1;
            count++;
        }

        public StudentSerial(String name, Integer age) {
            this();
            this.name = name;
            this.age = age;
        }
    }
}
