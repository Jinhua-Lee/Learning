package cn.io.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * 【文件客户端】<p>&emsp;
 * 模拟发送文件
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/17 15:42
 */
@Slf4j
public class BioFileClient {

    public static void main(String[] args) throws IOException {
        int serverPort = 8081;
        String server = "127.0.0.1";
        Socket socket = new Socket(server, serverPort);

        String uploadFilePath = "D:/io_test/bio_file/刻晴a.jpg";
        InputStream is = new FileInputStream(uploadFilePath);
        BufferedInputStream bis = new BufferedInputStream(is);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // 1. 写文件类型
        String[] split = uploadFilePath.split("\\.");
        String suffix = split[split.length - 1];
        log.info("[file client] to send a file, typed {}", suffix);
        dos.writeUTF(suffix);
        dos.flush();

        // 2. 写文件内容
        BufferedOutputStream bos = new BufferedOutputStream(dos);
        byte[] buff = new byte[1024];
        int length;
        while ((length = bis.read(buff)) > 0) {
            bos.write(buff, 0, length);
        }
        bos.flush();
        // 告诉服务器，已经发完了
        socket.shutdownOutput();
    }
}
