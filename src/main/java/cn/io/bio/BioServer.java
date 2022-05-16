package cn.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetAddress;
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

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {

        // 1. 服务端端口注册
        int serverPort = 8081;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket socket = serverSocket.accept();
        log.info("server open the port：{}", serverPort);

        // 2. 从Socket中得到流对象

        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        PrintWriter pw = new PrintWriter(bos);

        // 3. 读入
        String msg;
        while ((msg = br.readLine()) != null) {
            log.info("[server] receive msg = {}, from = {}", msg, socket.getInetAddress());

            // 将读入的消息回馈给client，TCP是全双工
            pw.println(socket.getInetAddress() + ", this is what you send：" + msg);
            pw.flush();
        }
    }
}
