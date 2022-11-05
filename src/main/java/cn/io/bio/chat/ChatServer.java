package cn.io.bio.chat;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 【聊天室】服务端<p>&emsp;
 * 1) 每次与客户端Socket建立连接，都会保存到一个socket列表；同理断开连接时将会移除，会从集合中一移除；<p>&emsp;
 * 2) 每收到一个消息，将消息转发到在线的Socket通道；<p>&emsp;
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/17 22:58
 */
@Slf4j
public class ChatServer {

    /**
     * 读多写少，一般是取出来发消息
     */
    private static final Set<Socket> ONLINE_SOCKETS = new CopyOnWriteArraySet<>();

    @SuppressWarnings("all")
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);

        // 自定义线程名字的线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            private int num;

            @Override
            @SuppressWarnings("NullableProblems")
            public Thread newThread(Runnable r) {
                return new Thread(r, "thread-" + (num++));
            }
        };

        // 创建线程池，处理请求
        ExecutorService execService = new ThreadPoolExecutor(
                2, 4, 500, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), threadFactory
        );

        // 交给线程处理
        while (true) {
            Socket accept = serverSocket.accept();
            execService.submit(new ServerHandlerWithOnlineSockets(accept, ONLINE_SOCKETS));
        }
    }

    @Slf4j
    private static class ServerHandlerWithOnlineSockets implements Runnable {

        private final Socket socket;
        private final Set<Socket> onlineSockets;

        private ServerHandlerWithOnlineSockets(Socket socket, Set<Socket> onlineSockets) {
            if (socket == null) {
                throw new IllegalArgumentException("socket must not be null.");
            }
            if (onlineSockets == null) {
                onlineSockets = new CopyOnWriteArraySet<>();
            }
            this.socket = socket;
            this.onlineSockets = onlineSockets;
            this.onlineSockets.add(socket);
        }

        @Override
        @SneakyThrows
        public void run() {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));

            String msg;
            while ((msg = br.readLine()) != null) {
                log.info("[sever] {} receive a msg: {}", Thread.currentThread().getName(), msg);
                // 转发到各个通道中
                for (Socket next : onlineSockets) {
                    // 如果是当前线程，则不用发了
                    if (this.socket == next) {
                        continue;
                    }
                    OutputStream os = next.getOutputStream();
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(bos));

                    bw.write(Thread.currentThread().getName() + " send msg: " + msg + "\n");
                    bw.flush();
                }
            }
        }
    }
}
