package com.se.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bio服务端，负责<p>&emsp;
 * 1. 开放端口，以接收客户端的数据；<p>&emsp;
 * 2. 打印收到的数据
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 27/04/2022 22:17
 */
@Slf4j
public class BioServer {

    public static void main(String[] args) throws IOException {
        // 1. 服务端的端口注册
        Socket socket;
        ServerSocket serverSocket;
        try {
            // 2. 监听8081端口
            int serverPort = 8081;
            serverSocket = new ServerSocket(serverPort);
            socket = serverSocket.accept();
            log.info("服务端开启端口监听：{}", serverPort);
        } catch (IOException e) {
            log.info("服务端异常：{}", e.getMessage());
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
        // 4. 将字节输入流包装为缓冲流
        bis = new BufferedInputStream(is);
        // 5. 包装为字符输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));

        // 6. 读入
        String msg;
        while ((msg = br.readLine()) != null) {
            log.info("我是服务端，我收到：{}", msg);
        }
    }
}
