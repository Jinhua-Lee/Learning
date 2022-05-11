package com.se.func;

/**
 * 抛异常
 *
 * @author Jinhua
 * @version 1.0
 * @date 2022/5/11 20:59
 */
public interface ThrowExceptionFunction {

    /**
     * 抛异常
     *
     * @param msg 异常信息
     */
    void throwException(String msg);

    /**
     * 普通静态方法，根据值返回一个过程
     *
     * @param res 值
     * @return 过程
     */
    static ThrowExceptionFunction isTrue(boolean res) {
        return msg -> {
            if (res) {
                throw new RuntimeException(msg);
            }
        };
    }

    /**
     * 测试主函数
     *
     * @param args 参数
     */
    static void main(String[] args) {
        isTrue(true).throwException("something wrong.");
    }
}


