package com.designpattern.decorator.animal;

/**
 * 不睡觉的超级动物
 *
 * @author Jinhua
 */
public class NonSleep extends SuperAnimal {
    public NonSleep(Animal animal) {
        super(animal);
    }

    @Override
    public void sleep() {
        super.sleep();
        System.out.println("睡五分钟，工作24小时");
    }
}
