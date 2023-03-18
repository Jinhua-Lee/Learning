package cn.io.bio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 测试【Socket】的accept
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/28 10:42
 */
@Slf4j
public class SocketAcceptTest {

    /**
     * 保存与客户端对应的socket对象
     */
    private static final List<Socket> SOCKETS = new ArrayList<>();

    /**
     * 结论：<p>&emsp;
     * 客户端每通过new Socket连接到服务端，服务端就通过accept创建一个与之对应的Socket
     *
     * @throws InterruptedException 中断异常
     */
    @Test
    @DisplayName(value = "测试【accept】方法产生socket")
    public void testCreateByAccept() throws InterruptedException {

        int clientNum = 5;

        Thread server = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8083)) {
                while (true) {
                    // accept方法等待客户端的连接
                    SOCKETS.add(serverSocket.accept());
                }
            } catch (IOException ignored) {
            }
        }, "server线程");

        Thread client = new Thread(() -> {
            try {
                for (int i = 0; i < clientNum; i++) {
                    // 客户端通过该方式连接到服务器，服务器就会通过accept方法产生一个Socket
                    new Socket("127.0.0.1", 8083);
                }
            } catch (IOException ignored) {
            }
        }, "client线程");

        server.start();
        client.start();
        TimeUnit.SECONDS.sleep(3L);

        Assertions.assertEquals(clientNum, SOCKETS.size());
    }
}
