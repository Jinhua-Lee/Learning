package com.designpattern.decorator.car;

/**
 * 超级汽车抽象类
 * @author Jinhua
 */
public abstract class SuperCar implements ICar {
	private ICar car;

	public SuperCar(ICar car) {
		this.car = car;
	}

	@Override
	public void move() {
		car.move();
	}
}
