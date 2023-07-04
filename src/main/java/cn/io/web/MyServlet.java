package cn.io.web;

/**
 * Servlet模拟
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:09
 */
public abstract class MyServlet {

    public static final String METHOD_GET = "GET";

    public void service(Req req, Resp resp) throws Exception {
        if (METHOD_GET.equalsIgnoreCase(req.getMethod())) {
            doGet(req, resp);
        } else {
            doPost(req, resp);
        }
    }

    /**
     * Get请求处理
     *
     * @param req  请求
     * @param resp 响应
     * @throws Exception 异常
     */
    public abstract void doGet(Req req, Resp resp) throws Exception;

    /**
     * Post请求处理
     *
     * @param req  请求
     * @param resp 响应
     * @throws Exception 异常
     */
    public abstract void doPost(Req req, Resp resp) throws Exception;
}
