package cn.io.web;

import lombok.AllArgsConstructor;

import java.io.IOException;
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

    @Override
    public void write(String s) throws IOException {
        // 请求行
        String sb = "HTTP/1.1 200 OK\n" +
                // 请求头
                "Content-Type: text/html;\n" +
                // 空行
                "\r\n" +
                // 响应体
                s;
        out.write(sb.getBytes(StandardCharsets.UTF_8));
    }
}
