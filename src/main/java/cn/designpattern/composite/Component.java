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
     *
     * @param component 组件
     */
    void add(Component component);

    /**
     * 删除组件
     *
     * @param component 组件
     */
    void remove(Component component);

    /**
     * 访问的方法，用于体现【一】和【多】的共同表现形式
     *
     * @param traverseConsumer 访问的方法
     */
    void eachChild(Consumer<Component> traverseConsumer);
}
