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
        log.info("succeed to connect to server, IP: {}, port: {}", server, serverPort);

//        writeOnce(socket.getOutputStream());
        // 读，依赖于TCP的（全）双工
//        readOnce(socket.getInputStream());
        writeAndReadCircular(socket);
        // 夯住
        for (; ; ) ;
    }

    /**
     * 单次发送
     *
     * @param os 字节输出流
     */
    private static void writeOnce(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        pw.println("Hello server, I am client! ");
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
    @SuppressWarnings("all")
    private static void writeAndReadCircular(Socket socket) throws IOException {
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);
        for (; ; ) {
            System.out.println("please input msg: ");

            // 发
            String line = sc.nextLine();
            pw.println(line);
            pw.flush();

            // 收
            readOnce(socket.getInputStream());
        }
    }
}
