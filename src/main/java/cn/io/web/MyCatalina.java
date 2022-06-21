package cn.io.web;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * 汤姆猫
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:36
 */
@Slf4j
public class MyCatalina {

    private final int port = 8080;
    private ServerSocket server;

    private final Map<String, MyGPServlet> servletMapping = new HashMap<>();
    ResourceBundle webProps = ResourceBundle.getBundle("web");

    private void init() {
        try {
            for (String key : webProps.keySet()) {
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webProps.getString(key);
                    String className = webProps.getString(servletName + ".className");

                    MyGPServlet obj = (MyGPServlet) Class.forName(className).newInstance();

                    servletMapping.put(url, obj);
                }
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ignored) {}
    }


    public void start() {
        init();

        try {
            server = new ServerSocket(this.port);
            log.info("MyCatalina已启动，监听端口是：{}", this.port);
            while (true) {
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception ignored){}
    }

    private void process(Socket client) throws Exception {
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        MyRequest req = new MyRequest(in);
        MyResponse resp = new MyResponse(out);

        String url = req.getUrl();
        if (servletMapping.containsKey(url)) {
            servletMapping.get(url).service(req, resp);
        } else {
            resp.write("404 - Not Found.");
        }

        out.flush();
        out.close();
        in.close();
        client.close();
    }

    public static void main(String[] args) {
        new MyCatalina().start();
    }
}
