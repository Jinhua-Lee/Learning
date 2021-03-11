package cn.designpattern.chain;

/**
 * 链中的处理结点的抽象
 *
 * @author Jinhua
 * @date 2021/3/11下午9:34
 */
public interface Handler {

    /**
     * 抽象职责链中单个结点的操作
     *
     * @param request 数据请求
     * @return 执行成功或失败
     */
    boolean handle(Object request);
}
