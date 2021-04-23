package cn.jvm.engine;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * 直接内存的使用
 * <p>
 * IO                   NIO(New IO / Non-blocking IO)
 * byte[] / char[]      Buffer
 * Stream               Channel
 *
 * @author Jinhua
 * @date 2021/4/19 21:50
 */
public class BufferTest {
    /**
     * 开辟直接内存大小：1GB
     */
    private static final int BUFFER_SIZE = 1024 * 1024 * 1024;

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
        System.out.println("直接内存分配完毕。");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("直接内存开始释放...");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
