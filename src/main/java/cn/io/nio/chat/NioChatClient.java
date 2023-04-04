package cn.io.nio.chat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 【NIO】聊天客户端
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/6/2 11:19
 */
@Slf4j
public class NioChatClient {

    private static final String LOCAL = "127.0.0.1";
    private static final int PORT = 8081;

    private final Selector selector;
    private final SocketChannel socketChannel;

    public NioChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(LOCAL, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    @SneakyThrows
    private void receive() {
        if (selector.select() > 0) {
            for (SelectionKey key : selector.selectedKeys()) {
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer bBuf = ByteBuffer.allocate(1024);
                    channel.read(bBuf);
                    log.info("[nio chat client] receive msg: {}", new String(bBuf.array()).trim());
                }
            }
        }
    }

    @SneakyThrows
    private void send() {
        log.info("[nio chat client] start to chat!");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            socketChannel.write(ByteBuffer.wrap(sc.nextLine().getBytes(StandardCharsets.UTF_8)));
        }
    }

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        NioChatClient chatClient = new NioChatClient();

        // 开两个线程，一个收，一个发
        Thread rec = new Thread(() -> chatClient.receive(), "rec-proc");
        Thread send = new Thread(() -> chatClient.send(), "send-proc");

        rec.start();
        send.start();
    }

}
