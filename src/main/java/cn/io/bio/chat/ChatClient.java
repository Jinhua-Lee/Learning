package cn.io.bio.chat;

import cn.io.BaseSocketUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 【聊天室】客户端<p>&emsp;
 * 1) 读线程：接收server的消息并打印；<p>&emsp;
 * 2) 写线程：接收用户命令行输入，向server发送消息。
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/17 22:59
 */
@Slf4j
public class ChatClient {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        // 创建客户端连接
        Socket socket = BaseSocketUtil.createSocket4Client();

        // 开两个线程，一个收，一个发
        Thread rec = new Thread(new ReceiveProc(socket), "rec-proc");
        Thread send = new Thread(new SendProc(socket), "send-proc");

        rec.start();
        send.start();
    }

    @Slf4j
    private static class ReceiveProc implements Runnable {

        private final Socket socket;

        public ReceiveProc(Socket socket) {
            if (socket == null) {
                throw new IllegalArgumentException("socket must not be null.");
            }
            this.socket = socket;
        }

        @Override
        @SneakyThrows
        @SuppressWarnings("all")
        public void run() {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));

            String line;
            while ((line = br.readLine()) != null) {
                log.info("[receive proc] receive msg: {}", line);
            }
        }
    }

    @Slf4j
    private static class SendProc implements Runnable {

        private final Socket socket;

        public SendProc(Socket socket) {
            if (socket == null) {
                throw new IllegalArgumentException("socket must not be null.");
            }
            this.socket = socket;
        }

        @Override
        @SneakyThrows
        @SuppressWarnings("all")
        public void run() {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            for (; ; ) {
                System.out.println("[send proc] please input msg: ");

                // 发
                String line = sc.nextLine();
                pw.println(line);
                pw.flush();
            }
        }
    }
}
