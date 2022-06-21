package cn.io.web.biz;

import cn.io.web.MyServlet;
import cn.io.web.Req;
import cn.io.web.Resp;

/**
 * 第二个功能
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:29
 */
public class SecondServlet extends MyServlet {

    @Override
    public void doGet(Req req, Resp resp) throws Exception {
        doPost(req, resp);
    }

    @Override
    public void doPost(Req req, Resp resp) throws Exception {
        resp.write("this is Second Servlet.");
    }
}
