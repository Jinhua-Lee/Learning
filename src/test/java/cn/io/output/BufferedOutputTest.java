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
 * 缓冲输出流测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/4 16:10
 */
@Slf4j
public class BufferedOutputTest extends BaseOutputStreamTest {

    private BufferedOutputStream bos;

    @BeforeEach
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    @SuppressWarnings("all")
    public void beforeEach() {
        String outputFilePath = "D:/io_test/buff_out_test.txt";
        File outputFile = new File(outputFilePath);

        if (!outputFile.exists() ||
                (outputFile.exists()
                        && outputFile.delete()
                        && outputFile.createNewFile())) {
        }
        outputStream = new FileOutputStream(outputFile, true);
        // 缓冲区大小
        int bufferSize = 15;
        bos = new BufferedOutputStream(outputStream, bufferSize);
        log.info("缓冲区的字节数是：{}", bufferSize);
    }

    @Test
    @DisplayName(value = "测试缓冲流，按字节输出。两次输出")
    @SneakyThrows
    public void testWriteByBytes() {
        byte[] aBytes = contentA.getBytes(StandardCharsets.UTF_8);
        bos.write(aBytes);
        log.info("第一次输出字节数：{}", aBytes.length);
        bos.flush();

        byte[] bBytes = contentB.getBytes(StandardCharsets.UTF_8);
        bos.write(bBytes);
        log.info("第二次输出字节数：{}", bBytes.length);
        // 第二次不用刷新，由于比缓冲区大而未采用缓冲区，不需要flush
//        bos.flush();
    }

    @Test
    @DisplayName(value = "测试缓冲流，按字符输出。两次输出")
    @SneakyThrows
    public void testWriteByChars() {
        // 注意用的是转换流的空间
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(bos), 15);
        bw.write(contentA);
        log.info("第一次输出字符数：{}", contentA.length());
        bw.flush();

        bw.write(contentB);
        log.info("第二次输出字符数：{}", contentB.length());
        // 第二次不用刷新，由于比缓冲区大而未采用缓冲区，不需要flush
//        bw.flush();
    }

    @AfterEach
    @SneakyThrows
    @DisplayName(value = "关闭流")
    public void afterAll() {
        bos.close();
        outputStream.close();
    }
}
