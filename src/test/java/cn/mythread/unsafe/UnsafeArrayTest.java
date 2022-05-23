package cn.mythread.unsafe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import sun.misc.Unsafe;

/**
 * 【Unsafe】数组操作测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/24 0:01
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UnsafeArrayTest {

    private static final Unsafe myUnsafe = MyUnsafeHolder.getMyUnsafe();

    /* 数组类型的测试 */
    private static final String[] STR_ARR = {"how", "old", "are", "you"};
    private static final int STR_ARR_BASE_OFFSET = myUnsafe.arrayBaseOffset(String[].class);
    private static final int STR_ARR_INDEX_SCALE = myUnsafe.arrayIndexScale(String[].class);

    @Test
    @DisplayName(value = "输出基本偏移地址，增量地址")
    public void beforeEach() {
        log.info("string数组的基本偏移量是{}, 增量地址是{}", STR_ARR_BASE_OFFSET, STR_ARR_INDEX_SCALE);
    }

    @Test
    @Order(value = 1)
    @DisplayName(value = "测试数组读取")
    public void testArrayRead() {
        // 读数组的指定索引元素
        final int index = 3;
        String str = (String) myUnsafe.getObject(STR_ARR,
                STR_ARR_BASE_OFFSET + (long) index * STR_ARR_INDEX_SCALE);
        log.info("数组的第{}个元素是{}", index + 1, str);
        Assertions.assertEquals(STR_ARR[index], str);
    }

    @Test
    @Order(value = 2)
    @DisplayName(value = "测试数组的写")
    public void testArrayWrite() {
        // 写数组的第i个元素
        final int index = 3;
        String replace = "you!";
        myUnsafe.putObject(STR_ARR, STR_ARR_BASE_OFFSET + (long) index * STR_ARR_INDEX_SCALE, replace);
        String reGet = (String) myUnsafe.getObject(STR_ARR,
                STR_ARR_BASE_OFFSET + (long) index * STR_ARR_INDEX_SCALE);
        log.info("再次获取数组的第{}个元素是{}", index + 1, reGet);
        Assertions.assertEquals(replace, reGet);
    }
}
