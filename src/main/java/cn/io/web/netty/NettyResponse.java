package cn.io.web.netty;

import cn.io.web.Resp;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import lombok.AllArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.nio.charset.StandardCharsets;

/**
 * Netty封装的Response
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 上午9:57
 */
@AllArgsConstructor
public class NettyResponse implements Resp {

    private ChannelHandlerContext ctx;

    public void write(String out) throws Exception {
        try {
            if (ObjectUtils.isEmpty(out)) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes(StandardCharsets.UTF_8))
            );
            response.headers().set("Content-Type", "text/html;");

            ctx.write(response);
        } finally {
            ctx.flush();
            ctx.close();
        }
    }
}
