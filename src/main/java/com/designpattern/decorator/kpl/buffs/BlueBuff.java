package com.designpattern.decorator.kpl.buffs;

import com.designpattern.decorator.kpl.Buff;
import com.designpattern.decorator.kpl.Hero;

/**
 * 带蓝Buff的英雄类
 * @author Jinhua
 */
public class BlueBuff extends Buff {
	private Hero hero;

	public BlueBuff (Hero hero) {
		super(hero);
	}

	@Override
    public int coolingTime() {
		return super.coolingTime() - 4;
	}
}
