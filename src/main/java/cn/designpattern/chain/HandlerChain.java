package cn.designpattern.chain;

import cn.designpattern.chain.impl.OperationHandler;
import cn.designpattern.chain.impl.ParamCheckHandler;
import cn.designpattern.chain.impl.PermissionCheckHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 维护职责的链结构
 *
 * @author Jinhua
 * @date 2021/3/11下午10:01
 */
public class HandlerChain {

    private final List<Handler> handlers;

    public HandlerChain() {
        handlers = new ArrayList<>();
    }

    /**
     * 添加处理结点
     *
     * @param handler 处理结点，要求非空
     */
    public boolean addHandler(Handler handler) {
        if (Objects.isNull(handler)) {
            return false;
        }
        return this.handlers.add(handler);
    }

    /**
     * 链中的处理，若有失败则立即退出
     *
     * @param data 待处理数据
     * @return 处理成功或失败
     */
    public boolean process(Object data) {
        for (Handler handler : handlers) {
            boolean handle = handler.handle(data);
            if (!handle) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 添加链结构
        HandlerChain chain = new HandlerChain();
        chain.addHandler(new ParamCheckHandler());
        chain.addHandler(new PermissionCheckHandler());
        chain.addHandler(new OperationHandler());

        // 执行链操作
        boolean process = chain.process("hello, world!");
        System.out.println(process);
    }
}
