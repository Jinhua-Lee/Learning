package com.designpattern.decorator.animal;

/**
 * 超级狗的
 * @author Jinhua
 */
public class DogTest {
	public static void main(String[] args) {
		Animal dog = new Dog();
		dog.sleep();
		dog.eat();

		System.out.println("--------------");
		Animal elecDog = new Electric(dog);
		elecDog.eat();
		elecDog.sleep();

		System.out.println("--------------");
		Animal elecNonsDog = new NonSleep(elecDog);
		elecNonsDog.eat();
		elecNonsDog.sleep();
	}
}
