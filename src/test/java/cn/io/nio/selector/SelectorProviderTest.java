package cn.io.nio.selector;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.*;
// import sun.nio.ch.EPollSelectorProvider;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

/**
 * 测试【SelectorProvider选择器提供器】
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/6/27 15:52
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class SelectorProviderTest {

    @Test
    @DisplayName(value = "通过【系统属性】配置，实例化SelectorProvider")
    public void testSystemPropertyConf() throws IOException {
        String providerKey = "java.nio.channels.spi.SelectorProvider";

        if (SystemUtils.IS_OS_LINUX){
            // Linux 默认是 EPollSelectorProvider
            String providerVal = "sun.nio.ch.PollSelectorProvider";
            System.setProperty(providerKey, providerVal);
        } else {
            // Windows 默认是 WEPollSelectorProvider
            String providerVal = "sun.nio.ch.WindowsSelectorProvider";
            System.setProperty(providerKey, providerVal);
        }

        Selector selector = Selector.open();
        SelectorProvider provider = selector.provider();
        System.clearProperty(providerKey);

        // 这里有权限问题，暂时只能通过断点方式去验证
        // Assertions.assertTrue(provider instanceof EPollSelectorProvider);
    }
}
