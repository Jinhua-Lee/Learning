package com.se.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Bio客户端，负责<p>&emsp;
 * 1. 监听服务端的端口；<p>&emsp;
 * 2. 向服务端发送数据
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 27/04/2022 22:17
 */
@Slf4j
public class BioClient {

    @SuppressWarnings("all")
    public static void main(String[] args) {
        OutputStream os;
        try {
            int serverPort = 8081;
            String server = "127.0.0.1";
            Socket socket = new Socket(server, serverPort);
            os = socket.getOutputStream();
            log.info("成功连接到服务端，IP: {}, 端口: {}", server, serverPort);
        } catch (IOException e) {
            log.info("客户端异常：{}", e.getMessage());
            return;
        }
        PrintWriter pw = new PrintWriter(os);
        pw.println("你好，我是客户端！");
        pw.flush();
        for (;;);
    }
}
