package cn.designpattern.decorator.animal;

/**
 * 不睡觉的狗 -> 增强功能的实现
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
