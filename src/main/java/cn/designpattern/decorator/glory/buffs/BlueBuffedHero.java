package cn.designpattern.decorator.glory.buffs;

import cn.designpattern.decorator.glory.BuffedHero;
import cn.designpattern.decorator.glory.Hero;

/**
 * 带蓝Buff的英雄类 -> 增强功能的实现
 *
 * @author Jinhua
 */
public class BlueBuffedHero extends BuffedHero {

    /**
     * 基本英雄的引用
     */
    private Hero hero;

    public BlueBuffedHero(Hero hero) {
        super(hero);
    }

    @Override
    public int coolingTime() {
        return super.coolingTime() - 4;
    }
}
