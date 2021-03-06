package cn.designpattern.builder.opt;

import cn.designpattern.builder.product.*;

import java.util.Objects;

/**
 * 产品生成器实现类<p>
 * 个人思路是，属性暂存到父类的属性中，当前类中新建的一个目标对象作为产品，最后在build()方法中校验并操作此对象
 *
 * @author Jinhua
 * @date 2021/3/4上午12:15
 */
public class ComputerBuilder extends Computer {

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
        Computer computer = null;

        int[] status = new int[3];
        status[0] = Objects.isNull(super.getMaster()) ? 0 : 1;
        status[1] = Objects.isNull(super.getScreen()) ? 0 : 1;
        status[2] = Objects.isNull(super.getKeyboard()) && Objects.isNull(super.getMouse()) ? 0 : 1;

        for (int i = 0; i < status.length - 1; i++) {
            // 不满足某些规则
            if (status[i] < status[i + 1]) {
                throw new RuntimeException("违背了先有Base的原则！！！");
            }
        }
        return new Computer(super.getMaster(), super.getScreen(), super.getKeyboard(), super.getMouse());
    }
}
