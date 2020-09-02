package com.designpattern.decorator.kpl.profession;

import com.designpattern.decorator.kpl.Hero;

/**
 * 刺客类
 * @author Jinhua
 */
public class Assassin implements Hero {
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

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public void setCoolingTime(int coolingTime) {
		this.coolingTime = coolingTime;
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
