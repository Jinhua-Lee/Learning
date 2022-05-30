package cn.io.nio.chat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;

/**
 * 【NIO】聊天服务端
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/30 10:58
 */
@Slf4j
public class NioChatServer {

    private final Selector selector;
    private final ServerSocketChannel serverChannel;
    private static final int PORT = 8083;

    public NioChatServer() throws IOException {
        selector = Selector.open();

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(PORT));

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    public void listen() throws IOException {
        while (selector.select() > 0) {
            Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
            while (itr.hasNext()) {
                SelectionKey next = itr.next();
                if (next.isAcceptable()) {
                    SocketChannel accept = serverChannel.accept();
                    accept.configureBlocking(false)
                            .register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    readClientAndTransferToOtherChannel(next);
                }
                itr.remove();
            }
        }
    }

    private void readClientAndTransferToOtherChannel(SelectionKey selectionKey) {
        SocketChannel channel;

        try {
            channel = (SocketChannel) selectionKey.channel();
            ByteBuffer bBuf = ByteBuffer.allocate(1024);
            if (channel.read(bBuf) > 0) {
                bBuf.flip();
                String msg = new String(bBuf.array(), 0, bBuf.remaining());
                log.info("[nio chat server] receive client message: {}", msg);
                transferToOtherChannel(channel, msg);
            }
        } catch (IOException ignored) {
            // 当客户端离线

        }
    }

    private void transferToOtherChannel(SocketChannel thisChannel, String msg) throws IOException {
        for (SelectionKey key : selector.selectedKeys()) {
            SocketChannel channel = (SocketChannel) key.channel();
            if (!Objects.equals(thisChannel, channel)) {
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                channel.write(buffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioChatServer chatServer = new NioChatServer();
        chatServer.listen();
    }
}
