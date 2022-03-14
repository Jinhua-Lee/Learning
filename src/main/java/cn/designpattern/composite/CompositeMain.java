package cn.designpattern.composite;

import lombok.extern.slf4j.Slf4j;

/**
 * 组合模式测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/3/14 22:07
 */
@Slf4j
public class CompositeMain {

    public static void main(String[] args) {
        // 构造根节点
        Component root = new Composite();
        root.add(new Leaf("叶子A"));

        Component compositeB = new Composite();
        compositeB.add(new Leaf("叶子B"));

        Component compositeC = new Composite();
        compositeC.add(new Leaf("叶子C"));
        compositeC.add(new Leaf("叶子D"));
        compositeB.add(compositeC);
        root.add(compositeB);

        root.eachChild(System.out::println);


    }
}
