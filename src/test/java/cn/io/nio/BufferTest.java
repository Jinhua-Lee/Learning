package cn.io.nio;

import org.junit.jupiter.api.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 测试缓冲区
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/21 13:15
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class BufferTest {

    private ByteBuffer bBuf;

    @BeforeEach
    @DisplayName(value = "向缓冲区放入初始化数据")
    public void beforeEach() {
        // 1. 初始化缓冲区
        bBuf = ByteBuffer.allocate(10);
        Assertions.assertEquals(0, bBuf.position());
        Assertions.assertEquals(10, bBuf.limit());
        Assertions.assertEquals(10, bBuf.capacity());

        // 2. 向缓冲区添加数据
        bBuf.put("ljh".getBytes(StandardCharsets.UTF_8));
        Assertions.assertEquals(3, bBuf.position());
        Assertions.assertEquals(10, bBuf.limit());
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试【翻转flip】方法")
    public void testFlip() {
        // flip()，切换已操作的，和未操作的
        bBuf.flip();
        Assertions.assertEquals(0, bBuf.position());
        Assertions.assertEquals(3, bBuf.limit());
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试【清除clear】方法的结果")
    public void testClear() {
        // clear，重置控制参数，而非清除数据
        bBuf.clear();
        Assertions.assertEquals(0, bBuf.position());
        Assertions.assertEquals(10, bBuf.limit());
    }

    @Test
    @Order(value = 3)
    @DisplayName(value = "测试【分片slice】")
    public void testSlice() {
        ByteBuffer slice = bBuf.slice();
        String content = "yyDs";
        // put内容到分片
        slice.put(content.getBytes(StandardCharsets.UTF_8));
        // 如果成立，则说明，指向同一内存区域
        Assertions.assertTrue(new String(bBuf.array(), 0, bBuf.limit()).contains(content));
    }
}
