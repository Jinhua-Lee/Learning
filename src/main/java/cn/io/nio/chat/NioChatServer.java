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
    private static final int NIO_SERV_PORT = 8081;

    public NioChatServer() throws IOException {
        selector = Selector.open();

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(NIO_SERV_PORT));

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    public void listen() throws IOException {
        while (selector.select() > 0) {
            Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
            while (itr.hasNext()) {
                SelectionKey next = itr.next();
                // 当前key的Channel是否准备好了接收一个新的连接
                if (next.isAcceptable()) {
                    SocketChannel accept = serverChannel.accept();
                    // 将读事件注册到该选择器上。
                    accept.configureBlocking(false)
                            .register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    // 可读事件，则从通道中读取事件，并转到其他通道中
                    readClientAndTransferToOtherChannel((SocketChannel) next.channel());
                }
                // 选择过的，后续就不用继续选择了
                itr.remove();
            }
        }
    }

    private void readClientAndTransferToOtherChannel(SocketChannel channel) {
        ByteBuffer bBuf = ByteBuffer.allocate(1024);
        try {
            if (channel.read(bBuf) > 0) {
                bBuf.flip();
                String msg = new String(bBuf.array(), 0, bBuf.remaining());
                log.info("[nio chat server] receive client message: {}", msg);
                // 与客户端的通信（客户端从WritableByteChannel中读取数据）
                transferToOtherChannel(channel, msg);
            }
        } catch (IOException ignored) {
            // 当客户端离线

        }
    }

    /**
     * 将【缓冲区】内容，写入该【选择器】的其他【可写通道】
     *
     * @param thisChannel 当前SocketChannel
     * @param msg         消息体
     * @throws IOException 将缓冲区内容写入通道的IO异常
     */
    private void transferToOtherChannel(SocketChannel thisChannel, String msg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            if (!(key.channel() instanceof WritableByteChannel channel)) {
                continue;
            }
            if (!Objects.equals(thisChannel, channel)) {
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));
                // 将缓冲区内容写入其他通道
                channel.write(buffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        NioChatServer chatServer = new NioChatServer();
        chatServer.listen();
    }
}
