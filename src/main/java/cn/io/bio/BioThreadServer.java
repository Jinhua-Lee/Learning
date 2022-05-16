package cn.io.bio;

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
            // 输入流
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));

            // 输出流
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            PrintWriter pw = new PrintWriter(bos);

            String msg;
            while ((msg = br.readLine()) != null) {
                log.info("[server] receive msg = {}, from = {}", msg, socket.getInetAddress());

                // 将读入的消息回馈给client，TCP是全双工
                pw.println(socket.getInetAddress() + ", this is what you send：" + msg);
                pw.flush();
            }
        }
    }
}
