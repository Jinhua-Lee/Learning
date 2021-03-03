package cn.designpattern.builder;

import cn.designpattern.builder.opt.ComputerBuilder;
import cn.designpattern.builder.product.*;

/**
 * 生成器模式优化的测试方法主类
 *
 * @author Jinhua
 * @date 2021/3/4上午12:23
 */
public class BuilderMain {

    public static void main(String[] args) {
        ComputerBuilder computerBuilder = new ComputerBuilder();

        Computer computer = computerBuilder.master(new Master())
                .keyboard(new Keyboard("RK键盘"))
                .mouse(new Mouse("雷柏鼠标"))
                .screen(new Screen("Samsung 曲面屏")).build();
        ;
    }
}
