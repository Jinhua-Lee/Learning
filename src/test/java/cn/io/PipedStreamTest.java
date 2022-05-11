package cn.io;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;

/**
 * 【管道流】测试
 *
 * @author Jinhua
 * @version 1.0
 * @date 05/05/2022 20:14
 */
@Slf4j
public class PipedStreamTest {

    private PipedInputStream pis;
    private PipedOutputStream pos;

    @BeforeEach
    @DisplayName(value = "初始化管道")
    @SneakyThrows
    public void beforeEach() {
        pis = new PipedInputStream(8);
        pos = new PipedOutputStream(pis);
    }

    @Test
    @DisplayName(value = "进行管道传输")
    public void pipe() {
        Thread out = new Thread(this::produce, "管道输出");
        Thread in = new Thread(this::consume, "管道输入");

        out.start();
        in.start();
    }

    @SneakyThrows
    private void produce() {
        int upper = 'A';
        while (true) {
            int letter = upper + new Random().nextInt(26);
            pos.write(letter);
            pos.flush();
            log.info("{} write a letter: {}", Thread.currentThread().getName(), (char) letter);
        }
    }

    @SneakyThrows
    @SuppressWarnings("all")
    public void consume() {
        while (true) {
            int letter = pis.read();
            log.info("{} receive a letter: {}", Thread.currentThread().getName(), (char) letter);
        }
    }
}
