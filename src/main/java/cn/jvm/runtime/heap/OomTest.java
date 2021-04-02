package cn.jvm.runtime.heap;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 内存溢出测试<p>&emsp;
 * Java heap space
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/2 15:29
 */
public class OomTest {

    @SneakyThrows
    public static void main(String[] args) {
        List<Picture> pictures = new ArrayList<>();
        while (true) {
            Thread.sleep(20L);
            pictures.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}

@Getter
class Picture {
    private final byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
