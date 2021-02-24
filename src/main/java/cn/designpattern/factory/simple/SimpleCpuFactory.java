package cn.designpattern.factory.simple;

import cn.designpattern.factory.product.cpu.AppleCpu;
import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.cpu.IntelCpu;

/**
 * 简单工厂 -> 工厂抽象
 *
 * @author Jinhua
 * @date 2021/2/25上午12:23
 */
public class SimpleCpuFactory {

    /**
     * 简单判断，产生具体产品，但返回父类抽象
     *
     * @param type 判断逻辑依据
     * @return 返回父类产品抽象
     */
    public static Cpu createCpu(int type) {
        if (type == 1) {
            return new IntelCpu();
        } else {
            return new AppleCpu();
        }
    }
}
