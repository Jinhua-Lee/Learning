package cn.designpattern.builder;

import cn.designpattern.builder.normal.ConcreteComputerBuilder;
import cn.designpattern.builder.normal.Director;
import cn.designpattern.builder.opt.ComputerBuilder;
import cn.designpattern.builder.opt.MasterBuilder;
import cn.designpattern.builder.product.*;
import cn.designpattern.factory.product.cpu.IntelCpu;
import cn.designpattern.factory.product.graphicscard.NvidiaGraphicsCard;

/**
 * 生成器模式优化的测试方法主类
 *
 * @author Jinhua
 * @date 2021/3/4上午12:23
 */
public class BuilderMain {

    public static void main(String[] args) {
        // 1.初始版
        Director director = new Director(new ConcreteComputerBuilder());
        director.build();
        Computer computer = director.getComputer();
        System.out.println(computer);

        // 2. 参考URIBuilder后的封装
        Master intelMaster =
                new MasterBuilder().name("Intel主机").cpu(new IntelCpu()).graphicsCard(new NvidiaGraphicsCard()).build();
        Computer anotherComputer = new ComputerBuilder()
                .master(intelMaster).screen(new Screen("三星屏"))
                .keyboard(new Keyboard("RK键盘cherry轴"))
                .mouse(new Mouse("雷柏鼠标")).build();
        System.out.println(anotherComputer);
    }
}
