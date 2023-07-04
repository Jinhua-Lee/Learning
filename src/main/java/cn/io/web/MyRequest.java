package cn.io.web;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;

/**
 * 请求模拟
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/21 下午7:14
 */
@Getter
public class MyRequest implements Req {

    private String method;
    private String uri;

    public MyRequest(InputStream input) {
        try {
            String content = "";
            byte[] buf = new byte[1024];
            int len;
            if ((len = input.read(buf)) > 0) {
                content = new String(buf, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.uri = arr[1].split("\\?")[0];
        } catch (IOException ignored) {
        }
    }
}
