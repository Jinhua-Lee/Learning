package cn.io.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 【文件通道】测试<p>&emsp;
 * 缓冲区未进行复用
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/21 22:21
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class FileChannelSimpleTest {

    private static final String NIO_TXT_FILE_PATH = "D:/io_test/nio_file/nio_txt_file.txt";
    private static final File NIO_TXT_FILE = new File(NIO_TXT_FILE_PATH);
    private static final ByteBuffer BYTE_BUF = ByteBuffer.allocate(20);
    private static final String CONTENT = "hello";

    @Test
    @Order(value = 1)
    @DisplayName(value = "通过通道写到文件")
    public void testSimpleWrite() throws IOException {
        // 原始输出流
        FileOutputStream fos = new FileOutputStream(NIO_TXT_FILE);
        // 流中拿到文件通道
        FileChannel fileChannel = fos.getChannel();

        BYTE_BUF.put(CONTENT.getBytes(StandardCharsets.UTF_8));
        BYTE_BUF.flip();

        // 通道将缓冲区的数据写出
        fileChannel.write(BYTE_BUF);
        fileChannel.close();
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "通过通道读文件到缓冲区")
    public void testSimpleRead() throws IOException {
        FileInputStream fis = new FileInputStream(NIO_TXT_FILE);
        FileChannel fileChannel = fis.getChannel();

        // 用通道读到缓冲区
        int read = fileChannel.read(BYTE_BUF);
        log.info("[channel read] read {} bytes from file {}", read, NIO_TXT_FILE_PATH);

        // 从缓冲区拿到数据
        BYTE_BUF.flip();
        String content = new String(BYTE_BUF.array(), 0, BYTE_BUF.remaining());
        log.info("[channel read] read content = {}", content);
    }
}
