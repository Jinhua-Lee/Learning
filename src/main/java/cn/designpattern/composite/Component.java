package cn.designpattern.composite;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * 组件的抽象
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/14 21:56
 */
public interface Component extends Serializable {

    /**
     * 组件
     * @param component 组件
     */
    void add(Component component);

    /**
     * 删除组件
     * @param component 组件
     */
    void remove(Component component);

    /**
     * 遍历的方法
     * @param traverseConsumer 遍历的方法
     */
    void eachChild(Consumer<Component> traverseConsumer);
}
