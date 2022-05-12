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
 * 【支持替换的对象流】测试
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/12 14:38
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ObjectStreamReplaceTest {

    private static final String replaceObjFilePath = "D:/io_test/object_replace_test.txt";
    private static final File replaceObjFile = new File(replaceObjFilePath);

    private static ObjectMapper mapper;

    @BeforeAll
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    @SuppressWarnings("all")
    public static void beforeAll() {

        if (!replaceObjFile.exists() ||
                (replaceObjFile.exists()
                        && replaceObjFile.delete()
                        && replaceObjFile.createNewFile())) {
        }

        mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试对象输出")
    @SneakyThrows
    public void testOutputReplaceObj() {
        ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(replaceObjFile));

        StudentReplaceSerial ljh = new StudentReplaceSerial("ljh", 25);
        log.info("out第一个替换对象：\n{}", mapper.writeValueAsString(ljh));

        objOutStream.writeObject(ljh);
        objOutStream.flush();
        objOutStream.close();
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试对象输入")
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void testInputReplaceObj() {
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(replaceObjFile));

        // 读上来的是数组
        List<Object> objList = (List<Object>) objInStream.readObject();
        objInStream.close();

        // 一些断言
        Assertions.assertEquals(2, objList.size());

        Assertions.assertEquals(String.class, objList.get(0).getClass());
        Assertions.assertEquals("ljh", objList.get(0));

        Assertions.assertEquals(Integer.class, objList.get(1).getClass());
        Assertions.assertEquals(25, objList.get(1));
    }

    @Getter
    @AllArgsConstructor
    private static class StudentReplaceSerial implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String name;
        private Integer age;

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
