package cn.designpattern.decorator.animal;

/**
 * 吃电的狗 -> 增强功能的实现
 *
 * @author Jinhua
 */
public class Electric extends SuperAnimal {
    public Electric(Animal animal) {
        super(animal);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("吃的是电");
    }

    @Override
    public void sleep() {
        super.sleep();
    }
}
