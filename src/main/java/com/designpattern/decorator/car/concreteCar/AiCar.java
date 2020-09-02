package com.designpattern.decorator.car.concreteCar;

import com.designpattern.decorator.car.ICar;
import com.designpattern.decorator.car.SuperCar;

/**
 * AI智能汽车类
 * @author Jinhua
 */
public class AiCar extends SuperCar {

	public AiCar(ICar car) {
		super(car);
	}

	public void autoMove() {
		System.out.println("自动跑");
	}

	@Override
	public void move() {
		super.move();
		autoMove();
	}
}
