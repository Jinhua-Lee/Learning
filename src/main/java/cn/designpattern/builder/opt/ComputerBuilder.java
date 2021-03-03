package cn.designpattern.builder.opt;

import cn.designpattern.builder.product.*;

import java.util.Objects;

/**
 * 产品生成器实现类<p>
 * 目前通过继承，将设置参数暂存于父类属性
 *
 * @author Jinhua
 * @date 2021/3/4上午12:15
 */
public class ComputerBuilder extends Computer {

    /**
     * 待输出的产品
     */
    private final Computer computer = new Computer();

    public ComputerBuilder master(Master master) {
        super.setMaster(master);
        return this;
    }

    public ComputerBuilder screen(Screen screen) {
        super.setScreen(screen);
        return this;
    }

    public ComputerBuilder keyboard(Keyboard keyboard) {
        super.setKeyboard(keyboard);
        return this;
    }

    public ComputerBuilder mouse(Mouse mouse) {
        super.setMouse(mouse);
        return this;
    }

    /**
     * 构建电脑的逻辑，包含复杂校验<p>
     * 构建规则：以下序列，后者的存在必须依赖于它前面的所有的都非空<p>
     * 1) 先有主机<p>
     * 2) 再有显示器<p>
     * 3) 后面 Keyboard 和 Mouse 顺序随意<p>
     * 举例：<p>
     * 1) 电脑1：主机 + 显示器 => 合法<p>
     * 2) 电脑2：主机 => 合法<p>
     * 3) 电脑3：显示器 + 鼠标 => 不合法<p>
     *
     * @return 规则校验后，构建出的电脑
     */
    public Computer build() {
        // 不满足某些规则
        if (Objects.isNull(super.getMaster())) {
            throw new RuntimeException("主机不能为空！！！");
        }
        this.computer.setMaster(super.getMaster());
        if (Objects.isNull(super.getScreen())) {
            throw new RuntimeException("不能为空！！！");
        }

        return this.computer;
    }
}
