package cn.io.web;

/**
 * 响应
 *
 * @author jinhua
 * @version 1.0
 * @date 2022/6/22 上午10:47
 */
public interface Resp {

    /**
     * 向客户端写出内容
     *
     * @param content 发送给客户端的内容
     * @throws Exception 发送异常
     */
    void write(String content) throws Exception;
}
