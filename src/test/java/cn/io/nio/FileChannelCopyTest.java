package cn.io.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * 【NIO文件复制】
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/22 0:45
 */
@Slf4j
public class FileChannelCopyTest {

    @Test
    @DisplayName(value = "通过通道进行文件复制")
    public void testCopy() throws IOException {
        String srcFile = "D:/io_test/nio_file/刻晴a.jpg";
        String desFile = "D:/io_test/nio_file/receive/刻晴a.jpg";
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(desFile);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        // 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            // 必须先清空缓冲区，再写
            buffer.clear();
            int read = inChannel.read(buffer);
            if (read == -1) {
                break;
            }
            // 切换读写模式
            buffer.flip();
            outChannel.write(buffer);
        }
        inChannel.close();
        outChannel.close();
        log.info("[channel copy] file copy succeed!");
    }

    @Test
    @DisplayName(value = "测试分散和聚集")
    public void testScatterGatherCopy() throws IOException {
        String srcFile = "D:/io_test/nio_file/nio_scatter_gather_src.txt";
        String desFile = "D:/io_test/nio_file/receive/nio_scatter_gather_desc.txt";
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(desFile);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer bufferA = ByteBuffer.allocate(2);
        ByteBuffer bufferB = ByteBuffer.allocate(3);

        ByteBuffer[] BYTE_BUFFER_ARR = {bufferA, bufferB};
        // 聚集到缓冲区
        inChannel.read(BYTE_BUFFER_ARR);
        // 切换
        Arrays.stream(BYTE_BUFFER_ARR).forEach(ByteBuffer::flip);
        // 一次写出
        outChannel.write(BYTE_BUFFER_ARR);

        inChannel.close();
        outChannel.close();
        log.info("[channel copy] file gathered copy succeed!");
    }

    @Test
    @DisplayName(value = "测试Transfer方式的复制")
    public void testTransfer() throws IOException {
        String srcFile = "D:/io_test/nio_file/nio_transfer_src.txt";
        String desFile = "D:/io_test/nio_file/receive/nio_transfer_desc.txt";
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(desFile);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        inChannel.transferTo(inChannel.position(), inChannel.size(), outChannel);
        // equal to
//        outChannel.transferFrom(inChannel, inChannel.position(), inChannel.size());

        inChannel.close();
        outChannel.close();
        log.info("[channel copy] file transfer copy succeed!");
    }
}
