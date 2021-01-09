package cn.designpattern.decorator.kpl.buffs;

import cn.designpattern.decorator.kpl.BuffedHero;
import cn.designpattern.decorator.kpl.Hero;

/**
 * 带蓝Buff的英雄类
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
