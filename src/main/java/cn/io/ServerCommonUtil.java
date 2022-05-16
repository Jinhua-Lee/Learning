package cn.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * Todo
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/17 0:04
 */
@Slf4j
public abstract class ServerCommonUtil {

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
