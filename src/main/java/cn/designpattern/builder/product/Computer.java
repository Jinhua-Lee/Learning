package cn.designpattern.builder.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * 复杂对象 -> 电脑
 *
 * @author Jinhua
 * @date 2021/3/2下午10:17
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Computer {

    /**
     * 主机
     */
    private Master master;

    /**
     * 显示器
     */
    private Screen screen;

    /**
     * 键盘
     */
    private Keyboard keyboard;

    /**
     * 鼠标
     */
    private Mouse mouse;

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
