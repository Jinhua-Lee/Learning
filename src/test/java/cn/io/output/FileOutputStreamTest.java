package cn.io.output;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 【文件输出流】测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/4 15:29
 */
@Slf4j
public class FileOutputStreamTest extends BaseOutputStreamTest {

    @BeforeEach
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    @SuppressWarnings("all")
    public void beforeEach() {
        String outputFilePath = "D:/io_test/out_test.txt";
        File outputFile = new File(outputFilePath);

        if (!outputFile.exists() ||
                (outputFile.exists()
                        && outputFile.delete()
                        && outputFile.createNewFile())) {
        }
        outputStream = new FileOutputStream(outputFile);
    }

    @Test
    @DisplayName(value = "测试【按字节】写出文件")
    @SneakyThrows
    public void testWriteByByte() {
        outputStream.write(contentA.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName(value = "测试【按字符】写出文件")
    @SneakyThrows
    public void testWriteByChar() {
        OutputStreamWriter ow;
        if (outputStream instanceof FileOutputStream) {
            ow = new OutputStreamWriter(outputStream);
            ow.write(contentA);
            ow.close();
        }
    }

    @AfterEach
    @SneakyThrows
    @DisplayName(value = "关闭流")
    public void afterAll() {
        outputStream.close();
    }
}
