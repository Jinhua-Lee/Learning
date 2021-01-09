package cn.designpattern.decorator.car;

/**
 * 基本移动功能的汽车实现类
 *
 * @author Jinhua
 */
public class Car implements ICar {

    /**
     * 实现基本功能：移动
     */
    @Override
    public void move() {
        System.out.println("陆地上跑");
    }
}
