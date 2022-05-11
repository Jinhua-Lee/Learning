package cn.io.output;

import java.io.OutputStream;

/**
 * 基础输出流测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/4 15:27
 */
public abstract class BaseOutputStreamTest {

    protected OutputStream outputStream;

    protected String contentA = "alice, ";
    protected String contentB = "bob, cindy, dale";
}
