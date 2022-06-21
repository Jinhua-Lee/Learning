package cn.io.web.netty;

import cn.io.web.Req;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Netty封装的Request
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 上午9:56
 */
@AllArgsConstructor
public class NettyRequest implements Req {

    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public String uri() {
        return request.uri();
    }

    @Override
    public String getMethod() {
        return request.method().name();
    }

    public Map<String, List<String>> parameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(uri());
        return decoder.parameters();
    }

    public String parameter(String key) {
        Map<String, List<String>> parameters = parameters();
        List<String> values = parameters.get(key);
        return ObjectUtils.isEmpty(values) ? null : values.get(0);
    }
}
