package cn.io.web;

/**
 * 响应
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 上午10:47
 */
public interface Resp {
    void write(String content) throws Exception;
}
