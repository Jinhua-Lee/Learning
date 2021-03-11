package cn.designpattern.chain.impl;

import cn.designpattern.chain.Handler;

/**
 * 处理结点的实现 -> 入参校验处理器
 *
 * @author Jinhua
 * @date 2021/3/11下午9:37
 */
public class ParamCheckHandler implements Handler {

    @Override
    public boolean handle(Object request) {

        return false;
    }
}
