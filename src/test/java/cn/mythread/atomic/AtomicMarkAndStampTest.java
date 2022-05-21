package cn.mythread.atomic;

import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 【原子标记】【原子版本】单元测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/21 12:15
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class AtomicMarkAndStampTest {

    /**
     * 原子标记
     */
    private AtomicMarkableReference<String> markableName =
            new AtomicMarkableReference<>("ljh", false);

    /**
     * 原子版本<p>&emsp;
     * 本质上就是比boolean类型标记多几个变化点
     */
    private AtomicStampedReference<String> stampedName =
            new AtomicStampedReference<>("ljh", 1);

    @AfterEach
    @DisplayName(value = "还原两个字段的信息")
    public void afterEach() {
        this.markableName = new AtomicMarkableReference<>("ljh", false);
        this.stampedName = new AtomicStampedReference<>("ljh", 1);
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试【值 + 标记】都相等，才设置变量")
    public void testByValueAndMark() {

        boolean set = markableName.compareAndSet("ljh", "lwk",
                false, true);
        // 预期：设置成功
        Assertions.assertTrue(set);
        // 预期：新的内容 + 被标记
        Assertions.assertEquals("lwk", markableName.getReference());
        Assertions.assertTrue(markableName.isMarked());

        boolean[] markHolder = new boolean[1];
        String name = markableName.get(markHolder);
        Assertions.assertTrue(markHolder[0]);
        Assertions.assertEquals("lwk", name);
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试【单独设置标记】")
    public void testSetMark() {
        boolean attemptRes = markableName.attemptMark("ljh", true);
        Assertions.assertTrue(attemptRes);
        Assertions.assertTrue(markableName.isMarked());
        Assertions.assertEquals("ljh", markableName.getReference());
    }

    @Test
    @Order(value = 3)
    @DisplayName(value = "测试【弱比较及测试】")
    public void testWeakCompareAndSet() {
        // [不推荐使用，除非搞清楚注释的意思了] weak compare and set
        // 困惑！weakCompareAndSet 这个方法最终还是调用 compareAndSet 方法。[版本: jdk-8u191]
        // 但是注释上写着 "May fail spuriously and does not provide ordering guarantees,
        // so is only rarely an appropriate alternative to compareAndSet."
        // todo 感觉有可能是 jvm 通过方法名在 native 方法里面做了转发
        boolean weakSetRes = markableName.weakCompareAndSet("ljh", "lwk",
                false, true);
        // 预期：设置成功
        Assertions.assertTrue(weakSetRes);
        // 预期：新值
        Assertions.assertTrue(markableName.isMarked());
        Assertions.assertEquals("lwk", markableName.getReference());
    }

    @Test
    @Order(value = 4)
    @DisplayName(value = "简单测试【原子版本】")
    public void testAtomicStamp() {
        boolean casRes = stampedName.compareAndSet("ljh", "lwk",
                1, 2);
        // 预期：设置成功
        Assertions.assertTrue(casRes);
        // 预期：新的内容 + 被标记
        Assertions.assertEquals("lwk", stampedName.getReference());
        Assertions.assertEquals(2, stampedName.getStamp());
    }
}
