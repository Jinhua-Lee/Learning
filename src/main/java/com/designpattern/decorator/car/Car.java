package com.designpattern.decorator.car;

/**
 * 实现了移动功能的汽车类
 * @author Jinhua
 */
public class Car implements ICar {
	@Override
	public void move() {
		System.out.println("陆地上跑");
	}
}
