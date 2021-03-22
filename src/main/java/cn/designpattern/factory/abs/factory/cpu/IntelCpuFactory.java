package cn.designpattern.factory.abs.factory.cpu;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.cpu.IntelCpu;

/**
 * 抽象产品A的工厂的实现 -> Intel的CPU
 *
 * @author Jinhua
 * @date 2021/2/22 23:01
 */
public class IntelCpuFactory implements CpuFactory {
    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }
}
