package cn.io.web.netty;

import cn.io.web.BaseCatalina;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 基于netty重构Tomcat
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 上午9:04
 */
@Slf4j
public class NettyCatalina extends BaseCatalina {

    @Override
    public void start() {
        init();

        // Boss与Worker线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();

            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel channel) {
                            // 无锁化串行编程
                            // Netty对Http的封装，有顺序要求

                            // 责任链，双向链表 Inbound OutBound
                            channel.pipeline().addLast(new HttpResponseEncoder());
                            channel.pipeline().addLast(new HttpRequestDecoder());
                            channel.pipeline().addLast(new MyTomcatHandler());
                        }
                    })
                    // 主线程，分配最大线程数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 子线程，保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = server.bind(PORT).sync();
            log.info("[netty catalina] started. listen port = {}", PORT);
            future.channel().closeFuture().sync();
        } catch (Exception ignored) {
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class MyTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest req) {
                log.info("[tomcat handler] hello, request is received now!");

                NettyRequest request = new NettyRequest(ctx, req);
                NettyResponse response = new NettyResponse(ctx);

                String uri = request.uri();
                if (servletMapping.containsKey(uri)) {
                    servletMapping.get(uri).service(request, response);
                } else {
                    response.write("404 - Not Found.");
                }
            }
        }
    }

    public static void main(String[] args) {
        new NettyCatalina().start();
    }
}
