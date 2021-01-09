package cn.designpattern.decorator.animal;

/**
 * 超级动物抽象类
 *
 * @author Jinhua
 */
public abstract class SuperAnimal implements Animal {
    /**
     * 普通动物的引用
     */
    private final Animal animal;

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

	public static void main(String[] args) {
		Animal dog = new Dog();
		dog.sleep();
		dog.eat();

		System.out.println("--------------");
		Animal electDog = new Electric(dog);
		electDog.eat();
		electDog.sleep();

		System.out.println("--------------");
		Animal nonSleepDog = new NonSleep(electDog);
		nonSleepDog.eat();
		nonSleepDog.sleep();
	}
}
