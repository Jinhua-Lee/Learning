package cn.designpattern.factory.abs.factory.cpu;

import cn.designpattern.factory.product.cpu.Cpu;

/**
 * 抽象产品A的工厂 -> CPU工厂
 *
 * @author Jinhua
 * @date 2021/2/22 22:51
 */
public interface CpuFactory {

    /**
     * 生产CPU的方法
     *
     * @return CPU对象
     */
    Cpu createCpu();
}
