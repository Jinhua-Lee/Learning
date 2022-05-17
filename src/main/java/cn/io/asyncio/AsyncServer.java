package cn.io.asyncio;

import cn.io.BaseServerCommonUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 异步处理方式的服务器<p>&emsp;
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/16 23:54
 */
public class AsyncServer {

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        int serverPort = 8081;
        ServerSocket serverSocket = new ServerSocket(serverPort);

        // 自定义线程名字的线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            private int num;

            @Override
            @SuppressWarnings("NullableProblems")
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-" + (num++));
            }
        };

        ExecutorService execService = new ThreadPoolExecutor(
                2, 4, 500, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), threadFactory
        );

        while (true) {
            execService.submit(new ServerHandlerProc(serverSocket.accept()));
        }
    }

    /**
     * 处理请求的过程
     */
    @Slf4j
    @AllArgsConstructor
    private static class ServerHandlerProc implements Runnable {

        private Socket socket;

        @Override
        @SneakyThrows
        public void run() {
            BaseServerCommonUtil.requestProc(socket);
        }
    }
}
