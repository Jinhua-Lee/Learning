package cn.designpattern.decorator.kpl.profession;

import cn.designpattern.decorator.kpl.Hero;
import lombok.Setter;

/**
 * 刺客类
 * @author Jinhua
 */
@Setter
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

	@Override
	public int attackPower() {
		return attackPower;
	}

	@Override
	public int coolingTime() {
		return coolingTime;
	}
}
