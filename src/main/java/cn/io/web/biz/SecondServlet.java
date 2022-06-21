package cn.io.web.biz;

import cn.io.web.MyGPServlet;
import cn.io.web.MyRequest;
import cn.io.web.MyResponse;

/**
 * 第二个功能
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:29
 */
public class SecondServlet extends MyGPServlet {

    @Override
    public void doGet(MyRequest req, MyResponse resp) throws Exception {
        doPost(req, resp);
    }

    @Override
    public void doPost(MyRequest req, MyResponse resp) throws Exception {
        resp.write("this is Second Servlet.");
    }
}
