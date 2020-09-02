package com.designpattern.decorator.kpl.buffs;

import com.designpattern.decorator.kpl.Buff;
import com.designpattern.decorator.kpl.Hero;

/**
 * 带红Buff的英雄
 * @author Jinhua
 */
public class RedBuff extends Buff {
	private Hero hero;

	public RedBuff(Hero hero) {
		super(hero);
	}

	@Override
    public int attackPower() {
		return super.attackPower() + 45;
	}

}
