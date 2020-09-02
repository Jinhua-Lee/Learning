package com.designpattern.decorator.kpl;

import com.designpattern.decorator.kpl.buffs.BlueBuff;
import com.designpattern.decorator.kpl.buffs.RedBuff;
import com.designpattern.decorator.kpl.profession.assassin.李白;

/**
 * kPL英雄与Buff装饰者模式
 * @author Jinhua
 */
public class KPLTest {
	public static void main(String[] args) {

		Hero hero = new 李白();
		System.out.println("\n初始\n------------------");
		System.out.println("攻击力：" + hero.attackPower() + "， 冷却时间：" + hero.coolingTime());
		System.out.println("------------------");

		System.out.println("加上红Buff：");
		Buff rbh = new RedBuff(hero);
		System.out.println("攻击力：" + rbh.attackPower() + "， 冷却时间：" + rbh.coolingTime());
		System.out.println("------------------");

		System.out.println("再加上蓝Buff：");
		Buff brbh = new BlueBuff(rbh);
		System.out.println("攻击力：" + brbh.attackPower() + "， 冷却时间：" + brbh.coolingTime());
		System.out.println("------------------");
	}
}
