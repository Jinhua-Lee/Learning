package cn.designpattern.chain.impl;

import cn.designpattern.chain.Handler;

/**
 * 处理结点的实现 -> 操作处理
 *
 * @author Jinhua
 * @date 2021/3/11下午9:41
 */
public class OperationHandler implements Handler {

    @Override
    public boolean handle(Object request) {
        return false;
    }
}
