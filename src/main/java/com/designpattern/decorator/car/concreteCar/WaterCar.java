package com.designpattern.decorator.car.concreteCar;

import com.designpattern.decorator.car.ICar;
import com.designpattern.decorator.car.SuperCar;

/**
 * 实现水里游功能的汽车类
 * @author Jinhua
 */
public class WaterCar extends SuperCar {
	public WaterCar(ICar car) {
		super(car);
	}

	public void swim() {
		System.out.println("水里游");
	}

	@Override
	public void move() {
		super.move();
		swim();
	}
}
