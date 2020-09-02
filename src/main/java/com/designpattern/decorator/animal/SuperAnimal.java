package com.designpattern.decorator.animal;

/**
 * 超级动物抽象类
 * @author Jinhua
 */
public abstract class SuperAnimal implements Animal {
	/**
	 * 普通的动物
	 */
	private Animal animal;

	public SuperAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public void eat() {
		animal.eat();
	}

	@Override
	public void sleep() {
		animal.sleep();
	}
}
