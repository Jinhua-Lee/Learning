package cn.designpattern.decorator.glory;

/**
 * 英雄定义 -> 普通功能定义
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
