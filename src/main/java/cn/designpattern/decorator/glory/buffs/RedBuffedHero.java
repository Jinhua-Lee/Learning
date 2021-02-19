package cn.designpattern.decorator.glory.buffs;

import cn.designpattern.decorator.glory.BuffedHero;
import cn.designpattern.decorator.glory.Hero;

/**
 * 带红Buff的英雄 -> 增强功能的实现
 *
 * @author Jinhua
 */
public class RedBuffedHero extends BuffedHero {
    private Hero hero;

    public RedBuffedHero(Hero hero) {
        super(hero);
    }

    @Override
    public int attackPower() {
        return super.attackPower() + 45;
    }

}
