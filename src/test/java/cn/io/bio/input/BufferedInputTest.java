package cn.io.bio.input;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 【缓冲输入流】的过程调试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/1 13:01
 */
@Slf4j
public class BufferedInputTest extends BaseInputStreamTest {

    private BufferedInputStream bis;

    @BeforeEach
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    public void beforeEach() {
        String bufferFilePath = "D:/io_test/buffer_test.txt";
        File bufferFile = new File(bufferFilePath);

        if (!bufferFile.exists() ||
                (bufferFile.exists()
                        && bufferFile.delete()
                        && bufferFile.createNewFile())) {
            FileWriter fWriter = new FileWriter(bufferFile);
            String content
                    = "alice, bob";
//                    = "你好鸭";
            fWriter.write(content);
            fWriter.flush();

            inputStream = new FileInputStream(bufferFile);
            // 每次读取两个字节
            int bufferSize = 4;
            bis = new BufferedInputStream(inputStream, bufferSize);
            log.info("写入文件的内容是：{}", content);
            log.info("写入文件的字节数是：{}", content.getBytes(StandardCharsets.UTF_8).length);
            log.info("缓冲区的字节数是：{}", bufferSize);
        }
    }

    @Test
    @DisplayName(value = "测试单字节读")
    @SneakyThrows
    public void testBufferedRead() {
        byte[] content = new byte[200];
        int read;
        for (int i = 0; i < content.length; i++) {
            // 这里多次读，都是从缓冲区中拿
            if ((read = bis.read()) != -1) {
                content[i] = (byte) read;
            } else {
                break;
            }
        }
        byte[] processed = processEmpty(content);
        log.info("读取到的内容是：{}", new String(processed, StandardCharsets.UTF_8));
    }

    @Test
    @DisplayName(value = "测试用户指定字节数组的读取")
    @SneakyThrows
    public void testBufferedReadBytes() {
        byte[] content = new byte[200];
        int readNum = bis.read(content);
        byte[] processed = processEmpty(content);
        log.info("API多字节读取，读到的内容是：{}", new String(processed, StandardCharsets.UTF_8));
        log.info("API多字节读取，读到的数量是：{}", readNum);
    }

    @Test
    @DisplayName(value = "测试用户指定字符数组的读取")
    @SneakyThrows
    public void testBufferedReadByChars() {
        BufferedReader br = new BufferedReader(new InputStreamReader(bis), 4);
        char[] chars = new char[200];
        int readNum = br.read(chars);
        byte[] bytes = processEmpty(new String(chars).getBytes(StandardCharsets.UTF_8));
        log.info("API多字符读取，读到的内容是：{}，共{}个字符", new String(bytes, StandardCharsets.UTF_8), readNum);
    }

    @AfterEach
    @SneakyThrows
    @DisplayName(value = "关闭流")
    public void afterAll() {
        bis.close();
        inputStream.close();
    }
}
