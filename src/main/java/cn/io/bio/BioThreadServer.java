package cn.io.bio;

import cn.io.BaseServerCommonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bio的Server端
 * 每接收到一个客户端单点socket请求，都交给一个线程来处理请求
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/15 17:03
 */
public class BioThreadServer {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        // 1. 建立服务端socket
        int serverPort = 8081;
        ServerSocket serverSocket = new ServerSocket(serverPort);

        // 2. 循环接收客户端请求
        while (true) {
            Thread th = new Thread(new ServerThreadHandler(serverSocket));
            th.start();
        }
    }

    @Slf4j
    private static class ServerThreadHandler implements Runnable {

        private final Socket socket;

        @SneakyThrows
        private ServerThreadHandler(ServerSocket serverSocket) {
            this.socket = serverSocket.accept();
        }

        @Override
        @SneakyThrows
        @SuppressWarnings("all")
        public void run() {
            BaseServerCommonUtil.requestProc(socket);
        }
    }
}
