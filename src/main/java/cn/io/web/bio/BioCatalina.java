package cn.io.web.bio;

import cn.io.web.BaseCatalina;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 汤姆猫
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:36
 */
@Slf4j
public class BioCatalina extends BaseCatalina {

    @Override
    @SuppressWarnings("all")
    public void start() {
        init();

        try {
            ServerSocket server = new ServerSocket(PORT);
            log.info("MyCatalina已启动，监听端口是：{}", PORT);
            while (true) {
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        new BioCatalina().start();
    }
}
