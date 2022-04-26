package com.se.io.bio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟BIO服务端
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 26/04/2022 01:22
 */
@Slf4j
public class BioDemo {

    @SneakyThrows
    public static Runnable serverRunnable() {
        return () -> {
            // 1. 服务端的端口注册
            Socket socket;
            ServerSocket serverSocket;
            try {
                // 2. 监听8081端口
                serverSocket = new ServerSocket(8081);
                socket = serverSocket.accept();
            } catch (IOException e) {
                return;
            }
            // 3. 从Socket中得到字节流对象
            BufferedInputStream bis;
            InputStream is;
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                return;
            }
            // 4. 讲字节输入流包装为缓冲流
            bis = new BufferedInputStream(is);
            // 5. 包装为字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));

            // 6. 读入
            br.lines().forEach(msg -> log.info("我是服务端，我收到：{}", msg));
        };
    }

    @SneakyThrows
    public static Runnable clientRunnable() {
        return () -> {
            OutputStream os;
            try {
                Socket socket = new Socket("localhost", 8081);
                os = socket.getOutputStream();
            } catch (IOException e) {
                return;
            }
            PrintWriter pw = new PrintWriter(os);
            pw.println("你好，我是客户端！");
            pw.flush();
        };
    }

    /**
     * 服务端通过main线程做
     *
     * @param args 入参
     */
    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(serverRunnable());
        executorService.submit(clientRunnable());
    }
}
