package cn.io.bio;

import cn.io.ServerCommonUtil;
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

        // 1. 服务端端口注册
        int serverPort = 8081;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket socket = serverSocket.accept();
        log.info("server open the port：{}", serverPort);

        ServerCommonUtil.requestProc(socket);
    }
}
