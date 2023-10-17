package cn.io.perf;

import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
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
@BenchmarkMode(value = Mode.AverageTime)
@SuppressWarnings("unused")
public class IoCopyPerformanceTest {

    public static final String FILE_IN_LINUX = "/home/jinhua/io_test/perf/file_in.txt";
    public static final String FILE_IN_WINDOWS = "D:/io_test/perf/file_in.txt";
    public static final String FILE_OUT_LINUX = "/home/jinhua/io_test/perf/file_out.txt";
    public static final String FILE_OUT_WINDOWS = "D:/io_test/perf/file_out.txt";

    public static String fileInPath() {
        if (SystemUtils.IS_OS_LINUX) {
            return FILE_IN_LINUX;
        } else {
            return FILE_IN_WINDOWS;
        }
    }

    public static String fileOutPath() {
        if (SystemUtils.IS_OS_LINUX) {
            return FILE_OUT_LINUX;
        } else {
            return FILE_OUT_WINDOWS;
        }
    }

    @SneakyThrows
    @BeforeAll
    public static void beforeAll() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileInPath()))) {
            for (int i = 0; i < 100_000; i++) {
                for (int j = 0; j < 10; j++) {
                    bw.write(String.valueOf(j));
                }
            }
        }
    }

    @SneakyThrows
    @Test
    @DisplayName(value = "多种IO的性能测试, JMH要求不能以Debug方式执行，会导致连接失败")
    public void testPerformance() {
        Options opt = new OptionsBuilder()
                .include(IoCopyPerformanceTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void readNormal() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileInPath()))) {
            while ((br.read()) != -1) {
            }
        }
    }

    @Benchmark
    public void readMmap() throws IOException {
        File file = new File(fileInPath());
        try (FileChannel fc = new RandomAccessFile(new File(fileInPath()), "r").getChannel()) {
            MappedByteBuffer mapIn = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length()).load();
            while (mapIn.hasRemaining()) {
                mapIn.get();
            }
        }
    }

    @Benchmark
    public void readSendfile() throws IOException {
        File file = new File(fileInPath());
        try (FileChannel fc = new RandomAccessFile(new File(fileInPath()), "r").getChannel()) {
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
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileInPath()));
             BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileOutPath()))) {
            int num;
            while ((num = br.read()) != -1) {
                bw.write(num);
            }
        }
    }

    @Benchmark
    public void readWriteMmap() throws IOException {
        File file = new File(fileInPath());
        try (FileChannel fc = new RandomAccessFile(new File(fileInPath()), "r").getChannel();
             FileChannel fo = new RandomAccessFile(new File(fileOutPath()), "rw").getChannel()) {
            MappedByteBuffer mapIn = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length()).load();
            MappedByteBuffer mapOut = fo.map(FileChannel.MapMode.READ_WRITE, 0, file.length()).load();
            while (mapIn.hasRemaining()) {
                mapOut.put(mapIn.get());
            }
        }
    }

    @Benchmark
    public void readWriteSendfile() throws IOException {
        File file = new File(fileInPath());
        try (FileChannel fc = new RandomAccessFile(new File(fileInPath()), "r").getChannel();
             FileChannel fo = new RandomAccessFile(new File(fileOutPath()), "rw").getChannel()) {
            long transferred = 0;
            while (transferred < file.length()) {
                transferred += fo.transferFrom(fc, 0, fc.size());
            }
        }
    }
}
