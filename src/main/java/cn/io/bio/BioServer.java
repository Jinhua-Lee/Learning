package cn.io.bio;

import cn.io.BaseSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
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

        // 1. 创建服务端的socket，开启端口监听
        Socket socket = BaseSocketUtil.createSocket4Server();

        // 2. 处理与客户端的请求
        BaseSocketUtil.requestProc(socket);
    }
}
