package com.designpattern.decorator.kpl;


/**
 * Buff抽象类继承自英雄，即是带buff的英雄抽象类
 * @author Jinhua
 */
public abstract class Buff implements Hero {

	/**
	 * 英雄
	 */
	private Hero hero;

	public Buff(Hero hero) {
		this.hero = hero;
	}

	@Override
	public int attackPower() {
		return hero.attackPower();
	}

	@Override
	public int coolingTime() {
		return hero.coolingTime();
	}
}
