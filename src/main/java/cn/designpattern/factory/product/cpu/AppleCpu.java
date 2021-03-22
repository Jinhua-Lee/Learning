package cn.designpattern.factory.product.cpu;

/**
 * 抽象产品A的实现 -> Apple CPU
 *
 * @author Jinhua
 * @date 2021/2/23 20:17
 */
public class AppleCpu implements Cpu {
    @Override
    public String getName() {
        return "Apple CPU";
    }
}
