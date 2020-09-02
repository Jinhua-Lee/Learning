package com.designpattern.decorator.kpl.profession.assassin;

import com.designpattern.decorator.kpl.profession.Assassin;

/**
 * 刺客实体类
 * @author Jinhua
 */
public class 李白 extends Assassin {

	public 李白() {
		System.out.println("李白");
	}

	public int getAttackPower() {
		return super.attackPower();
	}

	@Override
	public void setAttackPower(int attackPower) {
		super.setAttackPower(attackPower);
	}

	public int getCoolingTime() {
		return super.coolingTime();
	}

	@Override
	public void setCoolingTime(int coolingTime) {
		super.setCoolingTime(coolingTime);
	}
}
