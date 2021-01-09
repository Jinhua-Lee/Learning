package cn.designpattern.decorator.kpl.buffs;

import cn.designpattern.decorator.kpl.BuffedHero;
import cn.designpattern.decorator.kpl.Hero;

/**
 * 带红Buff的英雄
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
