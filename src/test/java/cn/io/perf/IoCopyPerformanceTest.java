package cn.io.perf;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 三种IO复制方式的性能比较<p>&emsp;
 * 1) 普通缓冲IO；<p>&emsp;
 * 2) mmap内存映射；<p>&emsp;
 * 3) 零拷贝sendfile；<p>&emsp;
 *
 * @author 鱼蛮
 * @version 1.0
 * @date 2021/9/28
 */
@Measurement(iterations = 5, time = 5)
@SuppressWarnings("unused")
public class IoCopyPerformanceTest {

    public static final String FILE_IN = "/home/jinhua/io_test/perf/file_in.txt";
    public static final String FILE_OUT = "/home/jinhua/io_test/perf/file_out.txt";

    @SneakyThrows
    @BeforeAll
    public static void beforeAll() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_IN))) {
            for (int i = 0; i < 100000; i++) {
                for (int j = 0; j < 10; j++) {
                    bw.write(String.valueOf(j));
                }
            }
        }
    }

    @SneakyThrows
    @Test
    @DisplayName(value = "多种IO的性能测试")
    public void testPerformance() {
        Options opt = new OptionsBuilder()
                .include(IoCopyPerformanceTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void readNormal() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_IN))) {
            while ((br.read()) != -1) {
            }
        }
    }

    @Benchmark
    public void readMmap() throws IOException {
        File file = new File(FILE_IN);
        try (FileChannel fc = new RandomAccessFile(new File(FILE_IN), "r").getChannel()) {
            MappedByteBuffer mapIn = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length()).load();
            while (mapIn.hasRemaining()) {
                mapIn.get();
            }
        }
    }

    @Benchmark
    public void readSendfile() throws IOException {
        File file = new File(FILE_IN);
        try (FileChannel fc = new RandomAccessFile(new File(FILE_IN), "r").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
            fc.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                buffer.get();
            }
        }
    }

    @Benchmark
    public void readWriteNormal() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_IN));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_OUT))) {
            int num;
            while ((num = br.read()) != -1) {
                bw.write(num);
            }
        }
    }

    @Benchmark
    public void readWriteMmap() throws IOException {
        File file = new File(FILE_IN);
        try (FileChannel fc = new RandomAccessFile(new File(FILE_IN), "r").getChannel();
             FileChannel fo = new RandomAccessFile(new File(FILE_OUT), "rw").getChannel()) {
            MappedByteBuffer mapIn = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length()).load();
            MappedByteBuffer mapOut = fo.map(FileChannel.MapMode.READ_WRITE, 0, file.length()).load();
            while (mapIn.hasRemaining()) {
                mapOut.put(mapIn.get());
            }
        }
    }

    @Benchmark
    public void readWriteSendfile() throws IOException {
        File file = new File(FILE_IN);
        try (FileChannel fc = new RandomAccessFile(new File(FILE_IN), "r").getChannel();
             FileChannel fo = new RandomAccessFile(new File(FILE_OUT), "rw").getChannel()) {
            long transferred = 0;
            while (transferred < file.length()) {
                transferred += fo.transferFrom(fc, 0, fc.size());
            }
        }
    }
}
