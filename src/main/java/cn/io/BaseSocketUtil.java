package cn.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket通用逻辑封装
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/17 0:04
 */
@Slf4j
public abstract class BaseSocketUtil {

    private static final int SERVER_PORT = 8081;

    public static Socket createSocket4Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        Socket socket = serverSocket.accept();
        log.info("succeed open server port: {}.", SERVER_PORT);
        return socket;
    }

    public static Socket createSocket4Client(String server, int port) throws IOException {
        server = ObjectUtils.isEmpty(server) ? "127.0.0.1" : server;
        port = port < 0 ? SERVER_PORT : port;
        Socket socket = new Socket(server, port);
        log.info("succeed to connect to server, IP: {}, port: {}", server, port);
        return socket;
    }

    public static void requestProc(Socket socket) throws IOException {
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
