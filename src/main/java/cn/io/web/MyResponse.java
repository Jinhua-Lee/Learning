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
public class MyResponse {

    private OutputStream out;

    public void write(String s) throws IOException {
        StringBuilder sb = new StringBuilder();
        // 请求行
        sb.append("HTTP/1.1 200 OK\n")
                // 请求头
                .append("Content-Type: text/html;\n")
                // 空行
                .append("\r\n")
                // 响应体
                .append(s);
        out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
}
