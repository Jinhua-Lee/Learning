package cn.io.input;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 【文件描述符】的一些单元测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/4 2:09
 */
@Slf4j
public class FileDescriptorTest {

    private FileOutputStream fos;

    @BeforeEach
    @SneakyThrows
    @DisplayName(value = "初始化流绑定的文件")
    @SuppressWarnings("all")
    public void beforeEach() {
        String syncFilePath = "D:/io_test/fd_sync.txt";
        File syncFile = new File(syncFilePath);

        if (!syncFile.exists() ||
                (syncFile.exists()
                        && syncFile.delete()
                        && syncFile.createNewFile())) {
        }
        fos = new FileOutputStream(syncFile);
    }

    @Test
    @DisplayName(value = "测试FD的同步sync()，不带内存缓冲区")
    @SneakyThrows
    public void testSyncNonBuffer() {
        FileDescriptor descriptor = fos.getFD();
        String content = "alice, bob";

        fos.write(content.getBytes(StandardCharsets.UTF_8));
        // 不生效，上一句执行时候，文件中已经有内容了
        descriptor.sync();
    }

    @Test
    @DisplayName(value = "测试FD的同步sync()，带内存缓冲区")
    @SneakyThrows
    public void testSyncWithBuffer() {
        BufferedOutputStream bos = new BufferedOutputStream(fos, 2);
        FileDescriptor descriptor = fos.getFD();
        String content = "alice, bob";

        bos.write(content.getBytes(StandardCharsets.UTF_8));
        // 不生效，上一句执行时候，文件中已经有内容了
        descriptor.sync();
    }
}
