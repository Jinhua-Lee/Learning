package com.se.except;

import com.se.except.ex.MyFirstException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Jinhua-Lee
 */
public class MyExceptionTest {

    @Test
    @DisplayName(value = "测试空构造")
    public void testConstruct() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new MyFirstException();
        }
    }

    @Test
    @DisplayName(value = "测试Message构造")
    public void testMessage() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new MyFirstException(e.getMessage());
        }
    }

    @Test
    @DisplayName(value = "测试异常构造")
    public void testThrow() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new MyFirstException(e);
        }
    }

    @Test
    @DisplayName(value = "测试Message和异常信息构造")
    public void testMessageAndThrow() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            throw new MyFirstException(e.getMessage(), e);
        }
    }
}
