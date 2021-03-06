package cn.designpattern.builder.opt;

import cn.designpattern.builder.product.Master;
import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 主机构建器<p>
 * 同上层对象构建<p>
 * 将所需参数存于父类对象，在此类builder()方法中进行判断和构建
 *
 * @author Jinhua
 * @date 2021/3/5下午10:19
 * @see ComputerBuilder
 */
public class MasterBuilder extends Master {

    public MasterBuilder name(String name) {
        super.setName(name);
        return this;
    }

    public MasterBuilder cpu(Cpu cpu) {
        super.setCpu(cpu);
        return this;
    }

    public MasterBuilder graphicsCard(GraphicsCard graphicsCard) {
        super.setGraphicsCard(graphicsCard);
        return this;
    }

    /**
     * 规则：<p>
     * 三个属性都不能为空
     *
     * @return 构件好的主机
     */
    public Master build() {
        if (StringUtils.isEmpty(super.getName())
                || Objects.isNull(super.getCpu())
                || Objects.isNull(super.getGraphicsCard())
        ) {
            throw new RuntimeException("构建失败，不允许存在空的属性！！！");
        }
        // 产品对象作为局部变量即可
        Master master = new Master();
        master.setName(super.getName());
        master.setCpu(super.getCpu());
        master.setGraphicsCard(super.getGraphicsCard());
        return master;
    }
}
