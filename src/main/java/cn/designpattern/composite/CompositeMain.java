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
        // 构造树形结构
        Component root = new Composite();
        root.add(new Leaf("叶子A"));

        Component compositeB = new Composite();
        compositeB.add(new Leaf("叶子B"));

        Component compositeC = new Composite();
        compositeC.add(new Leaf("叶子C"));
        compositeC.add(new Leaf("叶子D"));
        compositeB.add(compositeC);
        root.add(compositeB);

        log.info("===遍历root开始===");
        root.eachChild(System.out::println);
        log.info("===遍历root结束===");

        log.info("===遍历非叶子结点c开始===");
        compositeC.eachChild(System.out::println);
        log.info("===遍历非叶子结点c结束===");
    }
}
