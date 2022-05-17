package cn.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * 【文件服务器】<p>&emsp;
 * 模拟接收文件
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/17 15:42
 */
@Slf4j
public class BioFileServer {

    public static void main(String[] args) throws IOException {
        int serverPort = 8081;
        ServerSocket serverSocket = new ServerSocket(serverPort);
        Socket socket = serverSocket.accept();

        // 1. 接收文件类型
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String suffix = dis.readUTF();
        String fileName = UUID.randomUUID() + "." + suffix;
        log.info("[file server] receive a file, typed {}", suffix);

        // 2. 存放文件内容
        DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(new File("D:/io_test/bio_file/receive", fileName))
        );
        BufferedOutputStream bos = new BufferedOutputStream(dos);
        byte[] buff = new byte[1024];
        int length;
        while ((length = dis.read(buff)) > 0) {
            bos.write(buff, 0, length);
        }
        bos.flush();
    }
}
