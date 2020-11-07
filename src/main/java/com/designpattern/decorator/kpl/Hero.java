package com.designpattern.decorator.kpl;

/**
 * 英雄定义类
 *
 * @author Jinhua
 */
public interface Hero {

    /**
     * 英雄攻击属性
     *
     * @return 攻击力
     */
    int attackPower();

    /**
     * 英雄冷却属性
     *
     * @return 冷却时间
     */
    int coolingTime();
}
