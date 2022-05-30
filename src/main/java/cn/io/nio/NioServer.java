package cn.io.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 【NIO】服务端实现，非阻塞通信
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/27 10:41
 */
@Slf4j
public class NioServer {

    public static void main(String[] args) throws IOException {

        // 1. 【选择器】
        Selector selector = Selector.open();

        // 2. 【通道】
        //      2.1 打开【通道】
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //      2.2 非阻塞模式
        serverChannel.configureBlocking(false);
        //      2.3 绑定服务端口
        serverChannel.bind(new InetSocketAddress(8082));

        // 3. 【通道】都注册到【选择器】
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 4. 【选择器】轮询
        while (selector.select() > 0) {

            Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
            while (itr.hasNext()) {
                SelectionKey sKey = itr.next();

                // 接受事件，做选择键的初始化
                if (sKey.isAcceptable()) {
                    // 连接事件，需要产生一个与客户端对应的channel
                    SocketChannel accept = serverChannel.accept();
                    accept.configureBlocking(false);
                    // 【客户端通道】注册到【选择器】
                    accept.register(selector, SelectionKey.OP_READ);

                    // 读事件，通过选择键，拿它channel中的数据。
                } else if (sKey.isReadable()) {
                    // 可读事件，则从选择键中拿取Channel就可以了
                    SocketChannel channel = (SocketChannel) sKey.channel();

                    ByteBuffer bBuff = ByteBuffer.allocate(1024);
                    int len;
                    while ((len = channel.read(bBuff)) > 0) {
                        bBuff.flip();
                        log.info("[nio server] receive client data: {}", new String(bBuff.array(), 0, len));
                        bBuff.clear();
                    }
                }
                itr.remove();
            }
        }
    }
}
