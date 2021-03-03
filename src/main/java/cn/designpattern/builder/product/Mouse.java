package cn.designpattern.builder.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * 复杂对象的部件 -> 鼠标
 *
 * @author Jinhua
 * @date 2021/3/2下午10:24
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mouse {

    /**
     * 鼠标名
     */
    private String name;

    public Mouse(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
