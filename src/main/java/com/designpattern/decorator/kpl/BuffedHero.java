package com.designpattern.decorator.kpl;


import com.designpattern.decorator.kpl.buffs.BlueBuffedHero;
import com.designpattern.decorator.kpl.buffs.RedBuffedHero;
import com.designpattern.decorator.kpl.profession.assassin.李白;

/**
 * Buff抽象类继承自英雄，即是带buff的英雄抽象类
 * @author Jinhua
 */
public abstract class BuffedHero implements Hero {

	/**
	 * 英雄
	 */
	private Hero hero;

	public BuffedHero(Hero hero) {
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

	public static void main(String[] args) {

		Hero hero = new 李白();
		System.out.println("\n初始\n------------------");
		System.out.println("攻击力：" + hero.attackPower() + "， 冷却时间：" + hero.coolingTime());
		System.out.println("------------------");

		System.out.println("加上红Buff：");
		BuffedHero rbh = new RedBuffedHero(hero);
		System.out.println("攻击力：" + rbh.attackPower() + "， 冷却时间：" + rbh.coolingTime());
		System.out.println("------------------");

		System.out.println("再加上蓝Buff：");
		BuffedHero brbh = new BlueBuffedHero(rbh);
		System.out.println("攻击力：" + brbh.attackPower() + "， 冷却时间：" + brbh.coolingTime());
		System.out.println("------------------");
	}
}
