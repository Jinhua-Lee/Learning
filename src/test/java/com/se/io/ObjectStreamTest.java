package com.se.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.stream.StudentSerial;
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
public class ObjectStreamTest {

    private static ObjectOutputStream objOutStream;
    private static ObjectInputStream objInStream;
    private static ObjectMapper mapper;

    @BeforeAll
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    @SuppressWarnings("all")
    public static void beforeEach() {
        String objFilePath = "D:/io_test/object_test.txt";
        File objFile = new File(objFilePath);

        if (!objFile.exists() ||
                (objFile.exists()
                        && objFile.delete()
                        && objFile.createNewFile())) {
        }

        objOutStream = new ObjectOutputStream(new FileOutputStream(objFile));
        objInStream = new ObjectInputStream(new FileInputStream(objFile));

        mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试对象输出")
    @SneakyThrows
    public void testOutputObj() {
        StudentSerial ljh = new StudentSerial("ljh", "9569");
        StudentSerial lwk = new StudentSerial("lwk", "6868");
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
        StudentSerial ljh = (StudentSerial) objInStream.readObject();
        StudentSerial lwk = (StudentSerial) objInStream.readObject();
        objInStream.close();


        log.info("in第一个对象：\n{}", mapper.writeValueAsString(ljh));
        log.info("in第二个对象：\n{}", mapper.writeValueAsString(lwk));
    }

    @AfterAll
    @SneakyThrows
    @DisplayName(value = "关闭流")
    public static void afterAll() {
        objOutStream.close();
        objInStream.close();
    }
}
