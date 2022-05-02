package com.se.io.base;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

/**
 * InputStream输入流源码调试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/4/30 16:11
 */
@Slf4j
public class InputStreamTest extends BaseInputStreamTest {

    @BeforeEach
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    public void beforeEach() {
        String skipFilePath = "D:/io_test/skip_test.txt";
        File skipFile = new File(skipFilePath);

        if (!skipFile.exists() ||
                (skipFile.exists()
                        && skipFile.delete()
                        && skipFile.createNewFile())) {
            FileWriter fWriter = new FileWriter(skipFile);
            String content
                    = "alice, bob";
//                    = "你好鸭";
            fWriter.write(content);
            fWriter.flush();

            inputStream = new FileInputStream(skipFile);
            log.info("写入文件的内容是：{}", content);
            log.info("写入文件的字节数是：{}", content.getBytes(StandardCharsets.UTF_8).length);
        }
    }

    @Test
    @DisplayName(value = "测试Skip多个字节的用法")
    @SneakyThrows
    public void testSkipBytes() {
        byte[] bytes = new byte[50];

        // inputStream绑定的文件内容是小写英文字母字符，但需要转换为字节
        long skip = inputStream.skip(3);
        int read = inputStream.read(bytes);

        byte[] processedBytes = processEmpty(bytes);

        log.info("实际跳过的字节数是：{}", skip);
        log.info("读取到的内容是：{}", new String(processedBytes));
        log.info("读取到的字节数是：{}", read);
    }

    @Test
    @DisplayName(value = "测试【可以得到的字节数】【估计值】")
    @SneakyThrows
    public void testAvailable() {
        int available = inputStream.available();
        log.info("当次可以得到的字节数，预估为：{}", available);
    }

    @AfterEach
    @SneakyThrows
    @DisplayName(value = "关闭流")
    public void afterAll() {
        inputStream.close();
    }
}