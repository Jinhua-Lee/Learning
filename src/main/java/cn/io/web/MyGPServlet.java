package cn.io.web;

/**
 * Servlet模拟
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:09
 */
public abstract class MyGPServlet {

    public void service(MyRequest req, MyResponse resp) throws Exception {
        if ("GET".equalsIgnoreCase(req.getMethod())) {
            doGet(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    public abstract void doGet(MyRequest req, MyResponse resp) throws Exception;

    public abstract void doPost(MyRequest req, MyResponse resp) throws Exception;
}
