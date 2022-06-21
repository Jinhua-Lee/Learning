package cn.io.web.bio;

import cn.io.web.Resp;
import lombok.AllArgsConstructor;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 响应模拟
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:20
 */
@AllArgsConstructor
public class MyResponse implements Resp {

    private OutputStream out;

    public void write(String content) throws Exception {
        // 请求行
        String sb = "HTTP/1.1 200 OK\n" +
                // 请求头
                "Content-Type: text/html;\n" +
                // 空行
                "\r\n" +
                // 响应体
                content;
        out.write(sb.getBytes(StandardCharsets.UTF_8));
    }
}
