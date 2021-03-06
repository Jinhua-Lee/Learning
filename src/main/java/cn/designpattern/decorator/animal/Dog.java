package cn.designpattern.decorator.animal;

/**
 * 狗 -> 普通功能的实现对象
 *
 * @author Jinhua
 */
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("狗吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("狗睡觉");
    }
}
