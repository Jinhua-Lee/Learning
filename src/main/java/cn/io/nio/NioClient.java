package cn.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 【NIO】客户端实现，非阻塞通信
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/27 11:03
 */
@Slf4j
public class NioClient {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8082));
        channel.configureBlocking(false);
        ByteBuffer bBuf = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            log.info("[nio client] please input message: ");
            bBuf.put(scanner.nextLine().getBytes(StandardCharsets.UTF_8));
            bBuf.flip();
            channel.write(bBuf);
            bBuf.clear();
        }
    }
}
