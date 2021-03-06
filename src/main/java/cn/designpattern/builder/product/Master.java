package cn.designpattern.builder.product;

import cn.designpattern.factory.product.cpu.Cpu;
import cn.designpattern.factory.product.graphicscard.GraphicsCard;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 复杂对象的子对象 -> 主机
 *
 * @author Jinhua
 * @date 2021/3/2下午10:19
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Master {

    /**
     * 主机名
     */
    private String name;

    /**
     * Cpu
     */
    private Cpu cpu;

    /**
     * 显卡
     */
    private GraphicsCard graphicsCard;

    public Master(String name, Cpu cpu, GraphicsCard graphicsCard) {
        // 方式1. 构造方法中判断
        if (StringUtils.isEmpty(name) || Objects.isNull(cpu) || Objects.isNull(graphicsCard)) {
            throw new RuntimeException("不能有参数为空！！！");
        }
        this.name = name;
        this.cpu = cpu;
        this.graphicsCard = graphicsCard;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
