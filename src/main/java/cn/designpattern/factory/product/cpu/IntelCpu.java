package cn.designpattern.factory.product.cpu;

/**
 * 抽象产品A的实现 -> Intel CPU
 *
 * @author Jinhua
 * @date 2021/2/23 20:15
 */
public class IntelCpu implements Cpu {
    @Override
    public String getName() {
        return "Intel CPU";
    }
}
