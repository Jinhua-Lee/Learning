package com.designpattern.decorator.car;

import com.designpattern.decorator.car.concreteCar.FlyCar;
import com.designpattern.decorator.car.concreteCar.WaterCar;

public class CarTest {
	public static void main(String[] args) {
		System.out.println("基本功能");
		ICar iCar = new Car();
		iCar.move();

		System.out.println("---------------");
		System.out.println("增加新的功能：飞行");
		ICar flyCar = new FlyCar(iCar);
		flyCar.move();

		System.out.println("---------------");
		System.out.println("增加新的功能：水里游");
		ICar waterCar = new WaterCar(iCar);
		waterCar.move();

		System.out.println("---------------");
		System.out.println("增加两个新的功能，飞行，水里游");
		ICar waterCar2 = new WaterCar(new FlyCar(iCar));
		waterCar2.move();
	}
}
