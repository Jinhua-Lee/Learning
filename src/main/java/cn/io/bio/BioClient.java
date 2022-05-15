package cn.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Bio客户端，负责<p>&emsp;
 * 1. 连接服务端的端口；<p>&emsp;
 * 2. 向服务端发送数据
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 27/04/2022 22:17
 */
@Slf4j
public class BioClient {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        int serverPort = 8081;
        String server = "127.0.0.1";
        Socket socket = new Socket(server, serverPort);
        log.info("成功连接到服务端，IP: {}, 端口: {}", server, serverPort);

        writeOnce(socket.getOutputStream());
//        readOnce(socket.getInputStream());
//        writeCircular(socket.getOutputStream());
    }

    /**
     * 单次发送
     *
     * @param os 字节输出流
     */
    private static void writeOnce(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        pw.println("你好，我是客户端！");
        pw.flush();
    }

    /**
     * 单次接收
     *
     * @param is 字节输入瑞
     * @throws IOException IO异常
     */
    private static void readOnce(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));

        String line = br.readLine();
        log.info("[client] receive: {}", line);
    }

    /**
     * 多次发送
     *
     * @param os 字节输出流
     */
    private static void writeCircular(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            System.out.println("请发送消息：");
            String line = sc.nextLine();
            pw.println(line);
            pw.flush();
        }
    }
}
