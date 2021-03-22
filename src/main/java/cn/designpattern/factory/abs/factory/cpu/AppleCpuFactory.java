package cn.designpattern.factory.abs.factory.cpu;

import cn.designpattern.factory.product.cpu.AppleCpu;
import cn.designpattern.factory.product.cpu.Cpu;

/**
 * 抽象产品A的工厂的实现 -> Apple的CPU
 *
 * @author Jinhua
 * @date 2021/2/22 22:57
 */
public class AppleCpuFactory implements CpuFactory {
    @Override
    public Cpu createCpu() {
        return new AppleCpu();
    }
}
