package cn.io.web.biz;

import cn.io.web.MyGPServlet;
import cn.io.web.MyRequest;
import cn.io.web.MyResponse;

/**
 * 第一个功能
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:26
 */
public class FirstServlet extends MyGPServlet {

    @Override
    public void doGet(MyRequest req, MyResponse resp) throws Exception {
        doPost(req, resp);
    }

    @Override
    public void doPost(MyRequest req, MyResponse resp) throws Exception {
        resp.write("this is First Servlet.");
    }
}
