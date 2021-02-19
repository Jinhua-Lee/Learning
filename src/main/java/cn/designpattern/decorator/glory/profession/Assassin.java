package cn.designpattern.decorator.glory.profession;

import cn.designpattern.decorator.glory.Hero;
import lombok.Setter;

/**
 * 刺客类 普通实体的上层抽象
 *
 * @author Jinhua
 */
@Setter
public abstract class Assassin implements Hero {

    /**
     * 攻击值
     */
    private int attackPower = 120;

    /**
     * 冷却时间
     */
    private int coolingTime = 12;

    public Assassin() {
        System.out.println("我是刺客");
    }

    @Override
    public int attackPower() {
        return attackPower;
    }

    @Override
    public int coolingTime() {
        return coolingTime;
    }
}
