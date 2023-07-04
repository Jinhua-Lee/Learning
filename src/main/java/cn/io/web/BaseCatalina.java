package cn.io.web;

import cn.io.web.bio.MyRequest;
import cn.io.web.bio.MyResponse;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 通用功能的封装
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 上午9:11
 */
public abstract class BaseCatalina {

    public static final int PORT = 8080;

    protected final Map<String, MyServlet> servletMapping = new HashMap<>();
    protected ResourceBundle webProps = ResourceBundle.getBundle("web");

    protected void init() {
        try {
            for (String key : webProps.keySet()) {
                if (key.endsWith(".uri")) {
                    String servletName = key.replaceAll("\\.uri$", "");
                    String uri = webProps.getString(key);
                    String className = webProps.getString(servletName + ".className");

                    MyServlet obj = (MyServlet) Class.forName(className)
                            .getDeclaredConstructors()[0].newInstance((Object[]) null);

                    servletMapping.put(uri, obj);
                }
            }
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | InvocationTargetException ignored) {
        }
    }

    protected void process(Socket client) throws Exception {
        InputStream in = client.getInputStream();
        OutputStream out = client.getOutputStream();

        cn.io.web.bio.MyRequest req = new MyRequest(in);
        cn.io.web.bio.MyResponse resp = new MyResponse(out);

        String uri = req.getUri();
        if (servletMapping.containsKey(uri)) {
            servletMapping.get(uri).service(req, resp);
        } else {
            resp.write("404 - Not Found.");
        }

        out.flush();
        out.close();
        in.close();
        client.close();
    }

    /**
     * 启动Catalina服务
     */
    public abstract void start();
}
